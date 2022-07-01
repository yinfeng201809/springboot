package com.example.demo.spring.mvc;


import com.example.demo.domain.JsonViewUser;
import com.example.demo.domain.QueryParam;
import com.example.demo.domain.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用测试
 */
@RestController
@SessionAttributes("user")
public class AsynController {

    @Autowired
    @Qualifier("mvcTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    @GetMapping("/asyn")
    public Callable<User> getUser() {

        Callable<User> result = () -> {
            TimeUnit.SECONDS.sleep(5);
            User user = getOneDemoUser(1L);
            return user;
        };
        return result;
    }

    @GetMapping("webasyn")
    public WebAsyncTask<User> asynUser() {
        Callable<User> result = () -> {
            TimeUnit.SECONDS.sleep(5);
            User user = getOneDemoUser(1L);
            return user;
        };
        WebAsyncTask<User> asyncTask = new WebAsyncTask<>(TimeUnit.SECONDS.toMillis(2L), taskExecutor, result);

        asyncTask.onCompletion(()->{
            System.out.println("complete");
        });

        asyncTask.onTimeout(()->{
            return getOneDemoUser(22L);
        });

        asyncTask.onError(()->{
            return getOneDemoUser(33L);
        });

        return asyncTask;

    }
    @GetMapping("/matrix/{id}")
    public User getUser2(@PathVariable("id") Long id, @MatrixVariable(value = "color", pathVar="id") String color) {
        System.out.println(color);
        return getOneDemoUser(id);
    }

    private User getOneDemoUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @GetMapping("/defer")
    public DeferredResult<User> deferredResult(Long timeOut) {

        DeferredResult<User> deferredResult = new DeferredResult<User>(timeOut);

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deferredResult.setResult(getOneDemoUser(1L));
            deferredResult.setErrorResult(null);
        }).start();

        deferredResult.onTimeout(()->{
            deferredResult.setResult(getOneDemoUser(2L));
            return;
        });
        return deferredResult;
    }

    @GetMapping("/emit")
    public ResponseBodyEmitter responseEmitter(Long timeOut) {

        ResponseBodyEmitter emitter = new ResponseBodyEmitter(TimeUnit.SECONDS.toMillis(timeOut));

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                emitter.send(getOneDemoUser(11L));
                TimeUnit.SECONDS.sleep(1);
                emitter.send(getOneDemoUser(22L));
                TimeUnit.SECONDS.sleep(1);
                emitter.send(getOneDemoUser(33L));
                emitter.complete();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        return emitter;
    }

    @GetMapping("/modelattr/{id}/{userName}")
    public User responseEmitter2(@ModelAttribute User user) {
        return user;
    }

    @GetMapping("/modelattr2/{id}/{userName}")
    public User responseEmitter22(User user) {
        return user;
    }

    @GetMapping("/sessionattr")
    public User responseEmitter3(@SessionAttribute User user) {
        return user;
    }


    @PostMapping("upload")
    public User upload(@RequestPart("file") List<MultipartFile> multipartFile, Part part) throws IOException {
        if (!multipartFile.isEmpty()) {
            System.out.println("good");
        }
        return getOneDemoUser(11L);
    }
    @PostMapping("reqpart")
    public User reqPart(@RequestPart("file") User user) throws IOException {
        return user;
    }

    @PostMapping("/valid")
    public QueryParam queryParam(@Validated QueryParam queryParam, BindingResult bindingResult) {
        System.out.println(bindingResult);
        return queryParam;
    }

    @GetMapping("/jsonview")
    @JsonView(JsonViewUser.WithPasswordView.class)
    public JsonViewUser jsonViewUser() {

        return new JsonViewUser("eric", "7!jd#h23");
    }

//    @InitBinder
//    public void initBander(WebDataBinder webDataBinder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setLenient(false);
//        webDataBinder.registerCustomEditor(Date.class,new MyDateEditor());
////        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,false));
//
//    }

    public void request(HttpServletRequest request) {
        AsyncContext asyncContext=request.startAsync();
        asyncContext.dispatch();
        request.getDispatcherType();
    }

    /**
     * You can use StreamingResponseBody as the body in a ResponseEntity to customize the status and headers of the response.
     * @return
     */
    @GetMapping("/download")
    public StreamingResponseBody handle() {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                // write...
            }
        };
    }



}
