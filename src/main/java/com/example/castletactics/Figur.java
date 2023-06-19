package com.example.castletactics;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Figur extends Rectangle{
	private final boolean isWhite; //blank final
	//private Rectangle rect;
	private int side, row, col;
	private boolean lebt;
	private Rectangle me = this;

	protected Figur(GridPane pane, boolean isWhite, String path, int side, int col, int row, Spielverwaltung spv) {
		super(side,side,side,side);
		this.isWhite = isWhite;
		this.side = side;
		this.col = col;
		this.row = row;
		Image image1 = new Image(path,side,side,false,false);
		ImagePattern imagePattern = new ImagePattern(image1);
		this.setFill(imagePattern);
		pane.add(me,col,row);

		this.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				/* Figur wird geklicked und gezogen (drag start) */
				Dragboard db = me.startDragAndDrop(TransferMode.MOVE);

				/* Put a string on a dragboard */
				ClipboardContent content = new ClipboardContent();
				content.putImage(image1);
				db.setContent(content);
				me.setVisible(false);


				event.consume();
			}
		});

		this.setOnDragOver(new EventHandler<DragEvent>() {
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

		this.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* Sicht: bauer der geschmissen wird */

				boolean success = false;
				if (event.getGestureSource() != me &&
						event.getGestureSource() != this &&
						event.getDragboard().hasImage() &&
						zugErlaubt(pane, GridPane.getRowIndex((Node) event.getGestureSource()), GridPane.getColumnIndex((Node) event.getGestureSource()))) {
					/* allow for moving */
					event.acceptTransferModes(TransferMode.MOVE);

					schmeißen(pane);
					success = true;
				}
				event.setDropCompleted(success);

				event.consume();
			}
		});

		this.setOnDragDone(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* Sicht: Figur, die fallengelassen wird */
				if(event.getGestureTarget() != null
						&& event.getGestureTarget() instanceof Node
						&& zugErlaubt(pane, GridPane.getRowIndex((Node) event.getGestureTarget()), GridPane.getColumnIndex((Node) event.getGestureTarget()))) {

					int columnIndex = GridPane.getColumnIndex((Node) event.getGestureTarget()); // GestureTarget ist die Node, wo es draufgelegt wird
					int rowIndex = GridPane.getRowIndex((Node) event.getGestureTarget());
					pane.getChildren().remove(me);
					pane.add(me, columnIndex, rowIndex);


				}
				me.setVisible(true);

				event.consume();
			}
		});

	}

	public void schmeißen(GridPane pane)	{
		me.setVisible(false);
		pane.getChildren().remove(me);
	}

	public boolean zugErlaubt(Pane pane, int row, int col) {
		return true;
	}

	public Rectangle getRect(){
		return me;
	}
}
