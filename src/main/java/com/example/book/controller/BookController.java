package com.example.book.controller;

import com.example.book.entity.BookEntity;
import com.example.book.service.BookService;
import com.example.book.service.RoleService;
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
    @Resource
    private RoleService roleService;

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
     * 根据查询字段返回对应的排序列表
     *
     * @param shortList 需要查询的字段排序信息
     * @return 查询到的字段排序信息
     */
    @RequestMapping("sort_bookEntity_list")
    public List<BookEntity> sortBookEntityList(String shortList) {

//        return bookService.sortBookEntityList(shortList);
        return null;
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

    public static final Long 注册用户 = 1L;
    public static final Long 系统管理员 = 2L;

    /**
     * 根据用户名获取借书信息
     *
     * @param username 用户名
     * @return 借书列表
     */
    @RequestMapping("/getUserBookList")
    //@abc("admin,user")
    public List<BookEntity> getUserBookList(String username, String password) {

        //   User user = UserUtils.getLoginUser();

        if (roleService.hasRole(Long.parseLong(username), 注册用户)) {
            return bookService.userBookList(username, password);
        } else {
            return null;
        }
    }

    /**
     * 根据图书id下架图书
     *
     * @param bookId 图书id
     * @return Boolean
     */
    @RequestMapping("/offTheShelfBook")
    //@abc("user")
    public String offTheShelfBook(String bookId, String username, String password) {
        boolean ok = bookService.offBook(bookId, username, password);
        if (ok) {
            return "1";
        }
        return "0";
    }
}
