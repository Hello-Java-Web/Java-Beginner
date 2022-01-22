package com.example.book.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.book.entity.BookEntity;
import com.example.book.entity.UserEntity;
import com.example.book.mapper.BookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class BookService extends ServiceImpl<BookMapper, BookEntity> {


    //  @Autowired
    @Resource
    private UserService userService;

    /**
     * 图书添加的服务
     *
     * @param bookEntity 图书实体
     * @return 返回添加图书是否成功
     */
    public boolean addBook(BookEntity bookEntity, String username, String password) {
        if (bookEntity.getId() != null && bookEntity.getName() != null && !Objects.equals(bookEntity.getName(), "") && bookEntity.getWriter() != null && !Objects.equals(bookEntity.getWriter(), "")) {
            QueryWrapper<BookEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("id", bookEntity.getId());
            BookEntity book = this.getOne(wrapper);
            //判断保存的书编号不存在，存在返回false；
            if (book == null && Objects.equals(userService.userLevel(username, password), "0")) {
                Calendar nowDate = Calendar.getInstance();
                bookEntity.setUserId(0L);
                bookEntity.setFoundTime(nowDate.getTime());
                this.save(bookEntity);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 图书删除服务
     *
     * @param bookId 图书id
     * @return 保存是否成功
     */
    public boolean delBook(String bookId, String username, String password) {
        //新建一个book实体，查询是否存在该书
        QueryWrapper<BookEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("id", bookId);
        BookEntity bookEntity1 = this.getOne(wrapper);
        //存在就删除，否则返回错误
        if (bookEntity1 == null && !Objects.equals(userService.userLevel(username, password), "0")) {
            return false;
        } else {
            this.removeById(bookId);
            return true;
        }
    }


    /**
     * 更改/更新图书属性内容服务
     *
     * @param bookEntity 图书实体
     * @return 更新是否成功
     */
    public boolean upDataBook(BookEntity bookEntity) {
        if (bookEntity.getId() != null && bookEntity.getName() != null && !Objects.equals(bookEntity.getName(), "") && bookEntity.getWriter() != null && !Objects.equals(bookEntity.getWriter(), "")) {
            QueryWrapper<BookEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("id", bookEntity.getId());
            BookEntity bookEntity1 = this.getOne(wrapper);
            if (bookEntity1 == null) {
                return false;
            } else {
                this.update(bookEntity, wrapper);
                return true;
            }
        }
        return false;
    }


    /**
     * 借书服务
     *
     * @param bookId   书id
     * @param username 用户id
     * @return 返回boolean
     */
    public boolean user_book_Bind(String bookId, String username, String password) {
        if (bookId == null || bookId.equals("")) {
            return false;
        }
        //验证账户信息
        if (Objects.equals(userService.userLevel(username, password), "1")) {
            //查这本书是否存在
            QueryWrapper<BookEntity> wrapperBook = new QueryWrapper<>();
            wrapperBook.eq("id", bookId);
            BookEntity book = this.getOne(wrapperBook);
            //查这个用户是否存在
            QueryWrapper<UserEntity> wrapperUser = new QueryWrapper<>();
            wrapperUser.eq("username", username);
            UserEntity user = userService.getOne(wrapperUser);
            //判断书是否存在并在库，用户是否存在
            if (book.getUserId() != 0L || user.getId() == null || book.getState() == 0) {
                return false;
            }
            book.setUserId(user.getId());
            book.setState(0);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, +30);
            book.setUserReturnTime(calendar.getTime());
            this.update(book, wrapperBook);
            return true;
        }
        return false;
    }

    /**
     * 还书服务
     *
     * @param bookId   书id
     * @param username 用户id
     * @return 返回是否成功
     */
    public boolean user_book_Unbound(String bookId, String username, String password) {
        if (bookId == null || bookId.equals("")) {
            return false;
        }
        if (Objects.equals(userService.userLevel(username, password), "1")) {
            // 对于基于主键id的查询，可以直接用byId
            QueryWrapper<BookEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("id", bookId);
            BookEntity book = this.getOne(wrapper);
            // 检查图书是否存在
            QueryWrapper<UserEntity> wrapperUser = new QueryWrapper<>();
            wrapperUser.eq("username", username);
            UserEntity user = userService.getOne(wrapperUser);
            //判断用户是否借了本书
            if (Objects.equals(book.getUserId(), user.getId())) {
                //借书了，清除表关联，更新
                book.setState(1);
                book.setUserId(0L);
                book.setUserReturnTime(null);
                this.update(book, wrapper);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 根据书id获取一本书的全部信息
     *
     * @param id 书号
     * @return 一本书实体
     */
    public BookEntity getBookEntity(int id) {
        QueryWrapper<BookEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return this.getOne(wrapper);
    }

    /**
     * 根据用户名获取借书列表
     *
     * @param username 用户名
     * @return 用户名获取借书列表
     */
    public List<BookEntity> userBookList(String username, String password) {
        if (Objects.equals(userService.userLevel(username, password), "1")) {
            //获取用户id
            QueryWrapper<UserEntity> userWrapper = new QueryWrapper<>();
            userWrapper.eq("username", username);
            UserEntity user = userService.getOne(userWrapper);
            //通过用户id获取借书列表
            QueryWrapper<BookEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", user.getId());
            return this.list(wrapper);
        }
        return null;
    }

    /**
     * 根据图书id下架图书
     *
     * @param bookId 图书id
     * @return boolean
     */
    public boolean offBook(String bookId, String username, String password) {
        if (bookId != null && Objects.equals(userService.userLevel(username, password), "0")) {
            //通过图书id获取书
            QueryWrapper<BookEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("id", bookId);
            BookEntity book = this.getOne(wrapper);
            //更新图书状态“3”为下架状态
            book.setState(3);
            this.update(book, wrapper);
            return true;
        }
        return false;
    }

}
