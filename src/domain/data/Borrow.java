package domain.data;


import domain.data.person.Member;

import java.sql.Date;

public class Borrow {
    private Date loanDate;
    private Member member;
    private Book book;
    private boolean returned;

    public Date getLoanDate() {
        return loanDate;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public boolean isReturned() {
        return returned;
    }

    public Borrow(Book book, Member member, Date loanDate) {
        this.loanDate = loanDate;
        this.member = member;
        this.book = book;
        this.returned = false;
    }
}
