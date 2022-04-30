
public class Coordinates {
	private int row;
	private int column;

	public Coordinates(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public Coordinates(Coordinates other) {
		this(other.getRow(), other.getColumn());
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean issameas(Coordinates other) {
		return this.row == other.row && this.column == other.column;
	}

	public int distancefrom(Coordinates other) {
		int rowdifference = Math.abs(this.row - other.row);
		int columndifference = Math.abs(this.column - other.column);
		return rowdifference + columndifference;
	}

}
