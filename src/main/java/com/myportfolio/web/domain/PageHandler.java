package com.myportfolio.web.domain;

import org.springframework.web.util.UriComponentsBuilder;

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
    private int beginPage; // 화면에 보여줄 첫 페이지
    private int endPage; // 화면에 보여줄 마지막 페이지
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

    void print() {
        System.out.println("page = " + sc.getPage());
        System.out.print(showPrev ? "[PREV] " : "");
        for(int i=beginPage; i <= endPage; i++){
            System.out.print(i+" ");
        }
        System.out.println(showNext ? " [NEXT]" : "");
    }

    //지정된 페이지에 대한 네비게이션 쿼리스트링도 필요함 ->  PageHandler보다 option과 keyword가 있는SearchCondtion에 있는게 더 적합한거 같다.
//    public String getQueryString(Integer page){
//        return UriComponentsBuilder.newInstance()
//                .queryParam("page",page)    //지정된 페이지로 셋팅되게
//                .queryParam("pageSize",sc.getPageSize())
//                .queryParam("option",sc.getOption())
//                .queryParam("keyword",sc.getKeyword())
//                .build().toString();
//    }
//    // 검색결과를 받다가 목록으로 돌아올 때 값들을 유지해야됨
//    // ?page=1&pageSize=10&option=T&keyword="title"를 일일히 만들기 귀찮아서 만든메서드
//    public String getQueryString() {
////        return UriComponentsBuilder.newInstance()
////                .queryParam("page",sc.getPage())
////                .queryParam("pageSize",sc.getPageSize())
////                .queryParam("option",sc.getOption())
////                .queryParam("keyword",sc.getKeyword())
////                .build().toString();
//        //코드 중복으로 인해 위를 호출
//        return getQueryString(sc.getPage());
//    }

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
