package com.myportfolio.web.handler;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
   private Integer page = 1;
   private Integer pageSize =  10;
//   private Integer offset = 0; page와 pageSize로 계산하기 때문에 굳이 iv로 저장할 필요가 없는듯
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
      //org.spring.framework.web.util.UriComponentsBuilder는 여러 개의 파라미터들을 연결하여 URL 형태로 만들어 주는 기능을 가지고 있다.
      //UriComponentsBuilder가 UriComponents를 만들어줌
      return UriComponentsBuilder.newInstance()
              .queryParam("page",page)    //지정된 페이지로 셋팅되게
              .queryParam("pageSize", pageSize)
              .queryParam("option",option)
              .queryParam("keyword",keyword)
              .build().toString();
   }
   // 검색결과를 받다가 목록으로 돌아올 때 값들을 유지해야됨
   public String getQueryString() {
      //코드 중복으로 인해 위를 호출
      return getQueryString(page);
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
