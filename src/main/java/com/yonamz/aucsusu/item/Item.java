package com.yonamz.aucsusu.item;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.yonamz.aucsusu.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;


@Getter @Setter
@Entity
@NoArgsConstructor
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_no;

    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 100, nullable = false)
    private String writer;
    @Column(length = 100, nullable = false)
    private String content;
    @Column
    @ColumnDefault("0")
    private int starting_bid;
    @Column
    @ColumnDefault("0")
    private int winning_bid;
    @Column
    private String bidder;
    @Column
    private Date deadline;
    @Column(length = 100, nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp reg_date;
    @Column
    private String fileName;
    @Column
    @ColumnDefault("false")
    private boolean soldOut;
    @Column
    private String category;
    @Column
    @ColumnDefault("0")
    private int report;
    @Column(length = 100, nullable = false, updatable = false)
    private int cnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Item(Long item_no, String title, String writer, String content, int starting_bid, int winning_bid,
                String bidder, Date deadline, Timestamp reg_date, String category, String fileName, Boolean soldOut, int report, int cnt) {

        this.item_no = item_no;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.starting_bid = starting_bid;
        this.winning_bid = winning_bid;
        this.bidder = bidder;
        this.deadline = deadline;
        this.fileName = fileName;
        this.soldOut = soldOut;
        this.reg_date = reg_date;
        this.category = category;
        this.report = report;
        this.cnt = cnt;
    }
}