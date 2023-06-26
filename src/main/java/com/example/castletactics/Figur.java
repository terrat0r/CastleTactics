package com.example.castletactics;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Figur extends Rectangle{
	//private Rectangle rect;
	public final int side;
	public int row;
	public int col;
	private boolean lebt;
	private final Rectangle me = this;

	protected Figur(GridPane pane, boolean isWhite, String path, int side, int col, int row, Spielverwaltung spv) {
		super(side,side,side,side);
		//blank final
		this.side = side;
		this.col = col;
		this.row = row;
		Image image1 = new Image(path,side,side,false,false);
		ImagePattern imagePattern = new ImagePattern(image1);
		this.setFill(imagePattern);
		pane.add(me,col,row);

		this.setOnDragDetected(event -> {
			/* Figur wird geklicked und gezogen (drag start) */
			Dragboard db = me.startDragAndDrop(TransferMode.MOVE);

			/* Put a string on a dragboard */
			ClipboardContent content = new ClipboardContent();
			content.putImage(image1);
			db.setContent(content);
			me.setVisible(false);


			event.consume();
		});

		this.setOnDragOver(new EventHandler<>() {
			public void handle(DragEvent event) {
				/* Sicht: etwas im DnD-modus wird über die Figur gezogen */
				/* hier highlighting einbauen */
				if (event.getGestureSource() != me &&
						event.getGestureSource() != this &&
						event.getDragboard().hasImage()) {
					/* allow for moving */
					event.acceptTransferModes(TransferMode.MOVE);
				}

				event.consume();
			}
		});

		this.setOnDragDropped(new EventHandler<>() {
			public void handle(DragEvent event) {
				/* Sicht: bauer der geschmissen wird */

				boolean success = false;
				if (event.getGestureSource() != me &&
						event.getGestureSource() != this &&
						event.getDragboard().hasImage() &&
						event.getGestureTarget() instanceof Figur) {
					/* allow for moving */
					event.acceptTransferModes(TransferMode.MOVE);

					spv.zumSchmeißen = (Figur) event.getGestureTarget();
					success = true;
				}
				event.setDropCompleted(success);

				event.consume();
			}
		});

		this.setOnDragDone(event -> {
			/* Sicht: Figur, die fallengelassen wird */
			if (event.getGestureTarget() != null
					&& event.getGestureTarget() instanceof Figur) {

				spv.derSchmeißende = (Figur) event.getGestureSource();
				int srcRow = ((Figur) event.getGestureSource()).row;
				int srcCol = ((Figur) event.getGestureSource()).col;
				boolean zp = spv.zugPrüfen();
				if (event.getGestureSource() instanceof Bauer && zp)
					spv.enPassantPrüfen((Bauer) event.getGestureSource(), srcRow, srcCol);
				spv.enPassantKandidat = (Figur) event.getGestureSource();
			} else if (event.getGestureTarget() != null
					&& event.getGestureTarget() instanceof Rectangle
					&& event.getGestureSource() instanceof Figur
					&& zugErlaubt(((Figur) event.getGestureSource()).row,
						((Figur) event.getGestureSource()).col,
						GridPane.getRowIndex(((Rectangle) event.getGestureTarget())),
						GridPane.getColumnIndex((Rectangle) event.getGestureTarget()))) {
				int srcRow = ((Figur) event.getGestureSource()).row;
				int srcCol = ((Figur) event.getGestureSource()).col;
				if (event.getGestureSource() instanceof Bauer)
					spv.enPassantPrüfen((Bauer) event.getGestureSource(), srcRow, srcCol);
				spv.enPassantKandidat = (Figur) event.getGestureSource();
				pane.getChildren().remove((Rectangle) event.getGestureSource());
				pane.add((Rectangle) event.getGestureSource(), GridPane.getColumnIndex((Rectangle) event.getGestureTarget()), GridPane.getRowIndex((Rectangle) event.getGestureTarget()));
				((Figur) event.getGestureSource()).col = GridPane.getColumnIndex((Rectangle) event.getGestureTarget());
				((Figur) event.getGestureSource()).row = GridPane.getRowIndex((Rectangle) event.getGestureTarget());
			}
			me.setVisible(true);

			event.consume();
		});

	}

	public void schmeißen(GridPane pane)	{
		me.setVisible(false);
		pane.getChildren().remove(me);
	}

	public abstract boolean zugErlaubt(int row, int col, int rowDest, int colDest);

	public Rectangle getRect(){
		return me;
	}
}
