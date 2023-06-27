package com.example.castletactics;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Figur extends Rectangle{
	protected boolean moved = false;
	public final boolean isWhite;
	public final int side;
	public int row;
	public int col;
	private boolean lebt;
	private final Rectangle me = this;
	protected final Spielverwaltung spv;

	protected Figur(GridPane pane, boolean isWhite, String path, int side, int col, int row, Spielverwaltung spv) {
		super(side,side,side,side);
		this.isWhite = isWhite;
		//blank final
		this.side = side;
		this.col = col;
		this.row = row;
		this.spv = spv;
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
			boolean zug = false;
			if (event.getGestureTarget() != null
					&& event.getGestureTarget() instanceof Figur) {

				spv.derSchmeißende = (Figur) event.getGestureSource();
				int srcRow = ((Figur) event.getGestureSource()).row;
				int srcCol = ((Figur) event.getGestureSource()).col;
				boolean zp = spv.zugPrüfen();
				if (event.getGestureSource() instanceof Bauer && zp)
					spv.enPassantPrüfen((Bauer) event.getGestureSource(), srcRow, srcCol);
				spv.enPassantKandidat = (Figur) event.getGestureSource();
				zug = zp;
				moved = true;
			} else if (event.getGestureTarget() != null
					&& event.getGestureTarget() instanceof Rectangle
					&& event.getGestureSource() instanceof Figur
					&& zugErlaubt(((Figur) event.getGestureSource()).row,
						((Figur) event.getGestureSource()).col,
						GridPane.getRowIndex(((Rectangle) event.getGestureTarget())),
						GridPane.getColumnIndex((Rectangle) event.getGestureTarget()))
					&& ((Figur) event.getGestureSource()).isWhite == spv.whitePlays) {
				int srcRow = ((Figur) event.getGestureSource()).row;
				int srcCol = ((Figur) event.getGestureSource()).col;
				if (event.getGestureSource() instanceof Bauer)
					spv.enPassantPrüfen((Bauer) event.getGestureSource(), srcRow, srcCol);
				spv.enPassantKandidat = (Figur) event.getGestureSource();

				if(GridPane.getRowIndex((Rectangle) event.getGestureTarget()) != ((Figur) event.getGestureSource()).row || GridPane.getColumnIndex((Rectangle) event.getGestureTarget()) != ((Figur) event.getGestureSource()).col) {
					spv.figuren[GridPane.getRowIndex((Rectangle) event.getGestureTarget())][GridPane.getColumnIndex((Rectangle) event.getGestureTarget())] = spv.figuren[((Figur) event.getGestureSource()).row][((Figur) event.getGestureSource()).col];
					spv.figuren[((Figur) event.getGestureSource()).row][((Figur) event.getGestureSource()).col] = null;
					zug = true;
				}

				pane.getChildren().remove((Rectangle) event.getGestureSource());
				pane.add((Rectangle) event.getGestureSource(), GridPane.getColumnIndex((Rectangle) event.getGestureTarget()), GridPane.getRowIndex((Rectangle) event.getGestureTarget()));
				((Figur) event.getGestureSource()).col = GridPane.getColumnIndex((Rectangle) event.getGestureTarget());
				((Figur) event.getGestureSource()).row = GridPane.getRowIndex((Rectangle) event.getGestureTarget());

				moved = true;
			}
			me.setVisible(true);

			System.out.print("Runde:" + spv.round + "\t Spieler:");
			System.out.println(spv.whitePlays ? "Weiss" : "Schwarz");
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if(spv.figuren[i][j] != null) {
						switch (spv.figuren[i][j].getClass().getSimpleName()) {
						case "Bauer":
							System.out.print('B');
							break;
						case "Turm":
							System.out.print('T');
							break;
						case "Läufer":
							System.out.print('L');
							break;
						case "Springer":
							System.out.print('S');
							break;
						case "König":
							System.out.print('K');
							break;
						case "Königin":
							System.out.print('Q');
							break;
						default:
							System.out.print('?');
						}
					} else System.out.print(' ');
					//System.out.print((spv.figuren[i][j]==null?0:1) + " ");
				}
				System.out.println();
			}
			//System.out.println();
			if (zug) {
				spv.zugende();
			}
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

	public boolean hasMoved(){ return moved; }
}
