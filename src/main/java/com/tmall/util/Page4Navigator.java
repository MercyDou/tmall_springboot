package com.tmall.util;

import org.springframework.data.domain.Page;

import java.util.List;

public class Page4Navigator<T> {
    Page<T> pageFromJPA;
    int navigatePages;
    int totalPages;
    int number;
    long totalElements;
    int size;
    int numberOfElements;
    List<T> content;
    boolean isHasContent;
    boolean first;
    boolean last;
    boolean isHasNext;
    boolean isHasPrevious;
    int[] navigatepageNums;

    public Page4Navigator() {
        //构造函数，这个空的分页是为了 Redis 从 json格式转换为 Page4Navigator 对象而专门提供的
    }
    //pageFromJpa就是一个Page对象，navigatePages是分页栏要展示的有几个数字[2,3,4]即为3，一般为5，用Page4Navigator是为了对page进行分装
    //实现额外的功能，例如：将分页栏的分页数展示再上面。
    public Page4Navigator(Page<T> pageFromJPA, int navigatePages) {
        this.pageFromJPA = pageFromJPA;
        this.navigatePages = navigatePages;
        //总共有多少页
        totalPages = pageFromJPA.getTotalPages();
        //number:第几页
        number = pageFromJPA.getNumber();
        //总共有多少条数据
        totalElements = pageFromJPA.getTotalElements();
        //一页最多有多少条数据
        size = pageFromJPA.getSize();
        //当前页有多少数据 (与 size不同的是，最后一页可能不满 size 个)
        numberOfElements = pageFromJPA.getNumberOfElements();
        //数据集合List<T>
        content = pageFromJPA.getContent();
        //是否有数据Boolean
        isHasContent = pageFromJPA.hasContent();
        //是否时首页Boolean
        first = pageFromJPA.isFirst();
        //是否是最后一夜Boolean
        last = pageFromJPA.isLast();
        //是否有下一页Boolean
        isHasNext = pageFromJPA.hasNext();
        //是否有前一页Boolean
        isHasPrevious = pageFromJPA.hasPrevious();
        calcNavigatepageNums();

    }

    private void calcNavigatepageNums() {
        //数组里面包含要在底部分页栏显示的五个数字，如[4,5,6,7,8]
        int navigatepageNums[];
        //分出来的总页数
        int totalPages = getTotalPages();
        //第几页
        int num = getNumber();
        //当总页数小于或等于导航页码数时
        //总页数为3，分页栏size为5
        if (totalPages <= navigatePages) {
            navigatepageNums = new int[totalPages];
            for (int i = 0; i < totalPages; i++) {
                navigatepageNums[i] = i + 1;
            }
        }else {
            //当总页数大于导航页码数时,定义这个数组大小为5
            //navigatepageNums.size=5,totalpage=6,navigatePages=5
            navigatepageNums = new int[navigatePages];
            int startNum = num - navigatePages / 2;
            int endNum = num + navigatePages / 2;

            //分出的页数小于等于分页栏数组的size，
            //分页栏size为5，总页数大于5，当前页为[1,2]
            if (startNum < 1) {
                startNum = 1;
                //最前navigatePages页
                for (int i = 0; i < navigatePages; i++) {
                    //在第一页，分页栏为[1,2,3,4,5]
                    navigatepageNums[i] = startNum++;
                }
                //num=4,totalPages=6,startNum=1,endNum=7
                //[2,3,4,5,6]
                //当前页在分出的总页数位置是最后面分页栏数组的size中右，总共有10页，分页栏size为5，当前页在[9,10]
            } else if (endNum > totalPages) {
                //endNum=6
                endNum = totalPages;
                //从最后navigatePages页,即第6页开始，倒着给分页栏数组赋值6，5，4，3，2
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            }else{
                //所有中间页
                //总页数为10，分页栏size为5，当前页在[3,4,5,6,7,8]
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }
        this.navigatepageNums = navigatepageNums;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isHasNext() {
        return isHasNext;
    }

    public boolean isHasContent() {
        return isHasContent;
    }

    public boolean isHasPrevious() {
        return isHasPrevious;
    }

    public boolean isLast() {
        return last;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public int getNumber() {
        return number;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getSize() {
        return size;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public List<T> getContent() {
        return content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public Page<T> getPageFromJPA() {
        return pageFromJPA;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void setHasNext(boolean hasNext) {
        isHasNext = hasNext;
    }

    public void setHasContent(boolean hasContent) {
        isHasContent = hasContent;
    }

    public void setHasPrevious(boolean hasPrevious) {
        isHasPrevious = hasPrevious;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void setPageFromJPA(Page<T> pageFromJPA) {
        this.pageFromJPA = pageFromJPA;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
