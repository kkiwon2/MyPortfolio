package com.myportfolio.web.handler;

//게시판의 페이지를 결정하는 도메인
public class PageHandler {
//    private int page; // 현재 페이지
//    private int pageSize = 10; // 한 페이지당 게시물 갯수
//    private String option;
//    private String keyword;
    private SearchCondition sc;

    private int totalCnt; // 게시물의 총 갯수
    private int naviSize = 10;  //페이지 네비게이션의 크기
    private int totalPage; // 전체 페이지의 갯수
    private int beginPage; // 화면에 보여줄 네비게이션의 첫 페이지
    private int endPage; // 화면에 보여줄 네비게이션의 마지막 페이지
    private boolean showNext = false; // 이후를 보여줄지의 여부. endPage==totalPage이면, showNext는 false
    private boolean showPrev = false; // 이전을 보여줄지의 여부. beginPage==1이 아니면 showPrev는 false

    public PageHandler(int totalCnt, SearchCondition sc){
        this.totalCnt = totalCnt;
        this.sc = sc;
        doPaging(totalCnt, sc);
    }

    //페이징 계산을 하는데 필요한 메서드 - 게시물의 총 갯수, 현재페이지, 화면에 보여줄 게시물 갯수
    public void doPaging(int totalCnt, SearchCondition sc){
        this.totalCnt = totalCnt;

        totalPage = (int)Math.ceil(totalCnt /(double)sc.getPageSize());
        beginPage = (sc.getPage()-1) / naviSize * naviSize +1;
        endPage = Math.min((beginPage + naviSize -1), totalPage);
        showPrev = beginPage != 1;
        showNext = endPage <totalPage;
    }

    //페이지 네비게이션을 출력하는 메서드 테스트용
    void print() {
        System.out.println("page = " + sc.getPage());
        System.out.print(showPrev ? "[PREV] " : "");
        for(int i=beginPage; i <= endPage; i++){
            System.out.print(i+" ");
        }
        System.out.println(showNext ? " [NEXT]" : "");
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public SearchCondition getSc() {
        return sc;
    }

    public void setSc(SearchCondition sc) {
        this.sc = sc;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "sc=" + sc +
                ", totalCnt=" + totalCnt +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showNext=" + showNext +
                ", showPrev=" + showPrev +
                '}';
    }
}
