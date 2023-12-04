package com.Doggo.DoggoEx.entity;
import com.Doggo.DoggoEx.enums.BoardType;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "board_tb")
public class Board {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "board_type")
    private BoardType boardType;

    @Column(name = "board_comment")
    private String comment;

    @Column(name = "board_img")
    private String boardImg;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "board_reg_date")
    private Date regDate;
    @Column(name = "answer")
    private String answer;


}