package com.yangqh.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.gson.Gson;
import com.yangqh.exception.Base400Exception;
import com.yangqh.exception.BaseException;
import com.yangqh.properties.AdminProperties;
import com.yangqh.properties.RoleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author yangq
 * Create time in 2018/07/05 14:25
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private AdminProperties adminProperties;
    @Autowired
    private RoleProperties roleProperties;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String admin() {
        redisTemplate.opsForValue().set("adminProperties", new Gson().toJson(adminProperties));
        return adminProperties.getName() + adminProperties.getAge() + adminProperties.getSex();
    }

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public String test() {
        return roleProperties.getName() + roleProperties.getLevel() + "hello spring boot test1!";
    }

    @RequestMapping(value = "/exception", method = {RequestMethod.GET})
    public String exception(@RequestParam(name = "id") String id) {
        if ("1".equals(id)) {
            throw new BaseException();
        }
        if ("2".equals(id)) {
            throw new Base400Exception();
        }
        if ("3".equals(id)) {
            throw new NullPointerException();
        }
        return roleProperties.getName() + roleProperties.getLevel() + "hello spring boot test1!";
    }

    @RequestMapping("/imagecode.jpg")
    public void defaultKaptcha(HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

}
