package com.example.book.controller;

import com.example.book.entity.BookEntity;
import com.example.book.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
public class BookController {

    //@Autowired
    @Resource
    private BookService bookService;

    /**
     * 图书列表
     *
     * @return 图书列表
     */
    @RequestMapping("/bookList")
    public List<BookEntity> bookList() {

        return bookService.list();
    }


    /**
     * 增加新图书
     */
    @RequestMapping("/addBook")
    public String addBook(BookEntity bookEntity, String username, String password) {
        boolean ok = bookService.addBook(bookEntity, username, password);
        if (ok) {
            return "1";
        }
        return "0";
    }

    /**
     * 根据bookId查找一本书的详情
     *
     * @param id 书id号
     * @return 一本书全部信息
     */
    @RequestMapping("/getByIdBook")
    public BookEntity getByIdBook(int id) {
        return bookService.getBookEntity(id);
    }


    /**
     * 删除库中的图书
     * *@param bookEntity
     *
     * @return lz
     */
    @RequestMapping("/delBook")
    public String delBook(String bookId, String username, String password) {
        boolean ok = bookService.delBook(bookId, username, password);
        if (ok) {
            return "1";
        }
        return "0";
    }

    /**
     * 更改/更新图书属性内容
     * *@param bookEntity
     *
     * @return lz
     */
    @RequestMapping("/upDataBook")
    public String upDataBook(BookEntity bookEntity) {
        boolean ok = bookService.upDataBook(bookEntity);
        if (ok) {
            return "1";
        }
        return "0";
    }

    /**
     * 借书操作
     *
     * @param bookId   书id
     * @param username 用户id
     * @return 然后是否成功
     */
    @RequestMapping("/user_book")
    public String user_book(String bookId, String username, String password, String state) {
        boolean ok;
        if (Objects.equals(state, "1")) {
            ok = bookService.user_book_Bind(bookId, username, password);
        } else {
            ok = bookService.user_book_Unbound(bookId, username, password);
        }
        if (ok) {
            return "1";
        }
        return "0";
    }

    /**
     * 根据用户名获取借书信息
     *
     * @param username 用户名
     * @return 借书列表
     */
    @RequestMapping("/getUserBookList")
    public List<BookEntity> getUserBookList(String username, String password) {
        return bookService.userBookList(username, password);
    }

    /**
     * 根据图书id下架图书
     *
     * @param bookId 图书id
     * @return Boolean
     */
    @RequestMapping("/offTheShelfBook")
    public String offTheShelfBook(String bookId, String username, String password) {
        boolean ok = bookService.offBook(bookId, username, password);
        if (ok) {
            return "1";
        }
        return "0";
    }
}
