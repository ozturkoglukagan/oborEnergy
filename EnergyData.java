public class EnergyData {

    public int lineCount;
    public int momentaryRowCount;
    public int rowCount;
    public int insideDataCount;
    public int borderDataCount;
    public int emptyDataCount;

    public EnergyData(int lineCount,int momentaryRowCount,int rowCount,int insideDataCount,int borderDataCount,int emptyDataCount) {
        this.lineCount=lineCount;
        this.momentaryRowCount=momentaryRowCount;
        this.rowCount=rowCount;
        this.borderDataCount=borderDataCount;
        this.insideDataCount=insideDataCount;
        this.emptyDataCount=emptyDataCount;
    }
}
