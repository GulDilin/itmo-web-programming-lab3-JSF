public enum BDQuerys {
    GET_ALL("SELECT * FROM collection");

    private String textQuery;
    private BDQuerys(String textQuery){
        this.textQuery = textQuery;
    }

    public String getTextQuery() {
        return textQuery;
    }
}
