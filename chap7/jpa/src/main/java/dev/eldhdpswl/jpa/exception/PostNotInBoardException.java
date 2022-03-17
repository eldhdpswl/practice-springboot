package dev.eldhdpswl.jpa.exception;

//board에 있는 post가 없을떄
public class PostNotInBoardException extends BaseException{
    public PostNotInBoardException() {
        super("Post not in board");
    }
}
