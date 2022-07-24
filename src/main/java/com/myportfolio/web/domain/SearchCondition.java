package com.myportfolio.web.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
   private Integer page = 1;
   private Integer pageSize =  10;
//   private Integer offset = 0;
   private String keyword ="";
   private String option = "";

   public SearchCondition() {}
   public SearchCondition(Integer page, Integer pageSize, String keyword, String option) {
      this.page = page;
      this.pageSize = pageSize;
      this.keyword = keyword;
      this.option = option;
   }

   @Override
   public String toString() {
      return "SearchCondition{" +
              "page=" + page +
              ", pageSize=" + pageSize +
              ", offset=" + getOffset() +
              ", keyword='" + keyword + '\'' +
              ", option='" + option + '\'' +
              '}';
   }

   //지정된 페이지에 대한 네비게이션 쿼리스트링도 필요함
   // ?page=10&pageSize=10&option=A&keyword=title
   public String getQueryString(Integer page){
      return UriComponentsBuilder.newInstance()
              .queryParam("page",page)    //지정된 페이지로 셋팅되게
              .queryParam("pageSize", pageSize)
              .queryParam("option",option)
              .queryParam("keyword",keyword)
              .build().toString();
   }
   // 검색결과를 받다가 목록으로 돌아올 때 값들을 유지해야됨
   // ?page=1&pageSize=10&option=T&keyword="title"를 일일히 만들기 귀찮아서 만든메서드
   public String getQueryString() {
//        return UriComponentsBuilder.newInstance()
//                .queryParam("page",sc.getPage())
//                .queryParam("pageSize",sc.getPageSize())
//                .queryParam("option",sc.getOption())
//                .queryParam("keyword",sc.getKeyword())
//                .build().toString();
      //코드 중복으로 인해 위를 호출
      return getQueryString(3);
   }

   public Integer getPage() {
      return page;
   }

   public void setPage(Integer page) {
      this.page = page;
   }

   public Integer getPageSize() {
      return pageSize;
   }

   public void setPageSize(Integer pageSize) {
      this.pageSize = pageSize;
   }

   public Integer getOffset() {
      return (page -1) * pageSize;
   }


   public String getKeyword() {
      return keyword;
   }

   public void setKeyword(String keyword) {
      this.keyword = keyword;
   }

   public String getOption() {
      return option;
   }

   public void setOption(String option) {
      this.option = option;
   }
}
