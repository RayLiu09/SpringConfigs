package com.example.demo.validators;

import com.example.demo.models.BookModel;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BookModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (ObjectUtils.isEmpty(target)) {
            errors.rejectValue("name", "book.name.empty", "书名不能为空");
            return;
        }
        BookModel book = (BookModel) target;
        if (book.getPrice() > 200) {
            errors.rejectValue("price", "book.price.too.high", "价格不能超过200元");
        }
        if (book.getPublishDate() == null) {
            errors.rejectValue("publishDate", "book.publishDate.empty", "出版日期不能为空");
        }

        if (book.getAuthor() == null) {
            errors.rejectValue("author", "book.author.empty", "作者不能为空");
        }
    }
}
