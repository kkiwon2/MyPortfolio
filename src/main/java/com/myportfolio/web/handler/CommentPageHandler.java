package com.myportfolio.web.handler;

public class CommentPageHandler {
    private int totalCnt_C;   //총 댓글 갯수
    private int pageSize_C;   //한 페이지의 크기
    private int naviSize_C=10;   //댓글 페이지 네비게이션의 크기
    private int totalPage_C;  //전체 댓글 페이지의 갯수
    private int page_C;       //현재 댓글목록 페이지중 선택한놈
    private int beginPage_C;  //네비게이션의 첫번재 페이지
    private int endPage_C;    //네비게이션의 마지막 페이지
    private boolean showPrev_C;   //이전 페이지 버튼
    private boolean showNext_C;   //다음 페이지 버튼

    public CommentPageHandler(int totalCnt_C, int page_C){
        this(totalCnt_C, page_C, 10);
    }

    public CommentPageHandler(int totalCnt_C, int page_C, int pageSize_C){
        this.totalCnt_C = totalCnt_C;
        this.page_C = page_C;
        this.pageSize_C = pageSize_C;

        //총 페이지 갯수 구하기
        totalPage_C = (int)Math.ceil(totalCnt_C / (double)pageSize_C);
        //네비게이션바 첫번째 페이지 구하기
        beginPage_C = (page_C-1) / naviSize_C * naviSize_C +1;
        //네비게이션바 마지막
        endPage_C = Math.min((beginPage_C + naviSize_C -1), totalPage_C);
        showPrev_C = beginPage_C !=1;
        showNext_C = endPage_C < totalPage_C;
    }

    @Override
    public String toString() {
        return "CommentPageHandler{" +
                "totalCnt_C=" + totalCnt_C +
                ", pageSize_C=" + pageSize_C +
                ", naviSize_C=" + naviSize_C +
                ", totalPage_C=" + totalPage_C +
                ", page_C=" + page_C +
                ", beginPage_C=" + beginPage_C +
                ", endPage_C=" + endPage_C +
                ", showPrev_C=" + showPrev_C +
                ", showNext_C=" + showNext_C +
                '}';
    }

    public int getTotalCnt_C() {
        return totalCnt_C;
    }

    public void setTotalCnt_C(int totalCnt_C) {
        this.totalCnt_C = totalCnt_C;
    }

    public int getPageSize_C() {
        return pageSize_C;
    }

    public void setPageSize_C(int pageSize_C) {
        this.pageSize_C = pageSize_C;
    }

    public int getNaviSize_C() {
        return naviSize_C;
    }

    public void setNaviSize_C(int naviSize_C) {
        this.naviSize_C = naviSize_C;
    }

    public int getTotalPage_C() {
        return totalPage_C;
    }

    public void setTotalPage_C(int totalPage_C) {
        this.totalPage_C = totalPage_C;
    }

    public int getPage_C() {
        return page_C;
    }

    public void setPage_C(int page_C) {
        this.page_C = page_C;
    }

    public int getBeginPage_C() {
        return beginPage_C;
    }

    public void setBeginPage_C(int beginPage_C) {
        this.beginPage_C = beginPage_C;
    }

    public int getEndPage_C() {
        return endPage_C;
    }

    public void setEndPage_C(int endPage_C) {
        this.endPage_C = endPage_C;
    }

    public boolean isShowPrev_C() {
        return showPrev_C;
    }

    public void setShowPrev_C(boolean showPrev_C) {
        this.showPrev_C = showPrev_C;
    }

    public boolean isShowNext_C() {
        return showNext_C;
    }

    public void setShowNext_C(boolean showNext_C) {
        this.showNext_C = showNext_C;
    }


}
