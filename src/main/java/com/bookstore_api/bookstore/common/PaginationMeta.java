package com.bookstore_api.bookstore.common;


import org.springframework.data.domain.Page;

public class PaginationMeta {

    //    total count - ex:100
    private Long totalCount;
    //    page size   - 10
    private Integer pageSize;
    //    total page  - ex:10
    private Integer totalPage;
    //    page number - ex:1(1-10),2(11-20),3(21-30)...9,10(91-100)
    private Integer pageNumber;
    //    isLast()    - true/false
    private Boolean isLast;
    //    isFirst()   - true/false
    private Boolean isFirst;


    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Boolean getIsLast() {
        return isLast;
    }

    public void setIsLast(Boolean last) {
        isLast = last;
    }

    public Boolean getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Boolean first) {
        isFirst = first;
    }

    public <T> Object createPagination(Page<T> page) {

        PaginationMeta paginationMeta = new PaginationMeta();
        paginationMeta.setIsFirst(page.isFirst());
        paginationMeta.setIsLast(page.isLast());
        paginationMeta.setPageNumber(page.getNumber());
        paginationMeta.setPageSize(page.getSize());
        paginationMeta.setTotalCount(page.getTotalElements());
        paginationMeta.setTotalPage(page.getTotalPages());

        return paginationMeta;
    }
}
