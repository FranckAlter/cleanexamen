package domain.data;

import domain.data.Book;
import domain.data.person.Member;

import java.time.LocalDate;

public class Borrow {
    private LocalDate loanDate;
    private Member member;
    private Book book;
    private boolean returned;
}
