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
	private boolean lebt = true;
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
				int destRow = ((Figur) event.getGestureTarget()).row;
				int destCol = ((Figur) event.getGestureTarget()).col;

				if (event.getGestureSource() instanceof Bauer && spv.enPassantPrüfen((Bauer) event.getGestureSource(), srcRow, srcCol)) {
					move(destRow, destCol);
				} else if (spv.zugPrüfen()) {
					move(destRow, destCol);
				}
			} else if (event.getGestureTarget() != null
					&& event.getGestureTarget() instanceof Rectangle
					&& event.getGestureSource() instanceof Figur
					&& ((Figur) event.getGestureSource()).isWhite == spv.whitePlays) {
				int destRow = GridPane.getRowIndex((Rectangle) event.getGestureTarget());
				int destCol = GridPane.getColumnIndex((Rectangle) event.getGestureTarget());
				if (event.getGestureSource() instanceof Bauer && spv.enPassantPrüfen((Bauer) event.getGestureSource(), destRow, destCol)) {
					move(destRow, destCol);
				}
				spv.enPassantKandidat = this;

				if (zugErlaubt(((Figur) event.getGestureSource()).row,
						((Figur) event.getGestureSource()).col,
						GridPane.getRowIndex(((Rectangle) event.getGestureTarget())),
						GridPane.getColumnIndex((Rectangle) event.getGestureTarget()))) {
					move(GridPane.getRowIndex(((Rectangle) event.getGestureTarget())), GridPane.getColumnIndex(((Rectangle) event.getGestureTarget())));

					pane.getChildren().remove((Rectangle) event.getGestureSource());
					pane.add((Rectangle) event.getGestureSource(), GridPane.getColumnIndex((Rectangle) event.getGestureTarget()), GridPane.getRowIndex((Rectangle) event.getGestureTarget()));
					((Figur) event.getGestureSource()).col = GridPane.getColumnIndex((Rectangle) event.getGestureTarget());
					((Figur) event.getGestureSource()).row = GridPane.getRowIndex((Rectangle) event.getGestureTarget());
					moved = true;
				}
			}
			me.setVisible(true);

			System.out.print("Runde:" + spv.round + "\t Spieler:");
			System.out.println(spv.whitePlays ? "Weiss" : "Schwarz");
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if(spv.figuren[i][j] != null) {
						switch (spv.figuren[i][j].getClass().getSimpleName()) {
						case "Bauer" -> System.out.print('B');
						case "Turm" -> System.out.print('T');
						case "Läufer" -> System.out.print('L');
						case "Springer" -> System.out.print('S');
						case "König" -> System.out.print('K');
						case "Königin" -> System.out.print('Q');
						default -> System.out.print('?');
						}
					} else System.out.print(' ');
				}
				System.out.println();
			}
			event.consume();
		});
	}

	public void schmeißen(GridPane pane)	{
		me.setVisible(false);
		spv.geschmissen.add(this);
		pane.getChildren().remove(me);
	}

	public abstract boolean zugErlaubt(int row, int col, int rowDest, int colDest);

	public Rectangle getRect(){
		return me;
	}

	public boolean hasMovedOnce(){ return moved; }

	public void move(int destRow, int destCol){
		System.out.println("Move from: " + row + ", " + col);
		System.out.println("Move to: " + destRow + ", " + destCol);
		System.out.println("Move needed: " + (destRow != row || destCol != col));
		if (destRow != row || destCol != col) {
			spv.brett.textArea.appendText("Runde: " + spv.round + "\nMove:" + row + "," + col + " to " + destRow + "," + destCol + "\n");
			spv.schmeißen(destRow, destCol);
			spv.brett.pane.getChildren().remove(me);
			spv.brett.pane.add(me, destCol, destRow);
			spv.figuren[destRow][destCol] = spv.figuren[row][col];
			spv.figuren[row][col] = null;
			row = destRow;
			col = destCol;
			moved = true;
			spv.enPassantKandidat = this;
			spv.zugende();
		}
	}
}
