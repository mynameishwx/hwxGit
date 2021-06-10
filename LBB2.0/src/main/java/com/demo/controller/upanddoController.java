package com.demo.controller;

import com.demo.Service.dataService;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Controller
public class upanddoController {
    @Autowired
    dataService  dataService;

    //上传music
     @PostMapping("/upload")
    @ResponseBody
    String upload(@RequestParam(value = "file") MultipartFile file){
        String s=file.getName();
      return   dataService.musicuploadService(file);
    }
    //播放music
    @GetMapping("/play/{id}")
    public ModelAndView getAudio(@PathVariable("id") String id, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        System.out.println("id: " + id);

        //看不懂
        String range = request.getHeader("Range");
        if(range==null){
            System.out.println("range为空");
        }
        String[] rs = range.split("\\=");
        range = rs[1].split("\\-")[0];

        String path = request.getServletContext().getRealPath("/");
        System.out.println(path);
        File file = new File( "D:/LBB2.0/Data/music/" + id + ".mp3");
        if(!file.exists()) throw new RuntimeException("音频文件不存在 --> 404");
        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        long length = file.length();
        System.out.println("file length : " + length);
        // 播放进度
        int count = 0;
        // 播放百分比
        int percent = (int)(length * 1);

        int irange = Integer.parseInt(range);
        length = length - irange;

        response.addHeader("Accept-Ranges", "bytes");
        response.addHeader("Content-Length", length + "");
        response.addHeader("Content-Range", "bytes " + range + "-" + length + "/" + length);
        response.addHeader("Content-Type", "audio/mpeg;charset=UTF-8");

        int len = 0;
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            os.write(b, 0, len);
            count += len;
            if(count >= percent){
                break;
            }
        }
        System.out.println("count: " + count + ", percent: " + percent);
        fis.close();
        os.close();
        return null;
    }

   @GetMapping("/video")
    public void getVideo(HttpServletRequest request,HttpServletResponse response)

    {

//视频资源存储信息



        response.reset();

//获取从那个字节开始读取文件

        String rangeString = request.getHeader("Range");

        try {

//获取响应的输出流

            OutputStream outputStream = response.getOutputStream();

            File file = new File("D:/LBB2.0/src/main/WebApp/static/music/myvideotwo.mp4");

            if(file.exists()){

                RandomAccessFile targetFile = new RandomAccessFile(file, "r");

                long fileLength = targetFile.length();

//播放

                if(rangeString != null){

                    long range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));

//设置内容类型

                    response.setHeader("Content-Type", "video/mp4");

//设置此次相应返回的数据长度

                    response.setHeader("Content-Length", String.valueOf(fileLength - range));

//设置此次相应返回的数据范围

                    response.setHeader("Content-Range", "bytes "+range+"-"+(fileLength-1)+"/"+fileLength);

//返回码需要为206，而不是200

                    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

//设定文件读取开始位置(以字节为单位)

                    targetFile.seek(range);

                }else {//下载

//设置响应头，把文件名字设置好

                    response.setHeader("Content-Disposition", "attachment; filename="+"myvideotwo.mp4" );

//设置文件长度

                    response.setHeader("Content-Length", String.valueOf(fileLength));

//解决编码问题

                    response.setHeader("Content-Type","application/octet-stream");

                }

                byte[] cache = new byte[1024 * 300];

                int flag;

                while ((flag = targetFile.read(cache))!=-1){

                    outputStream.write(cache, 0, flag);

                }

            }else {

                String message = "file:"+"myvideotwo"+" not exists";

//解决编码问题

                response.setHeader("Content-Type","application/json");

                outputStream.write(message.getBytes(StandardCharsets.UTF_8));

            }

            outputStream.flush();

            outputStream.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

    }

    //    下载music
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    @ResponseBody
    public  String  download(HttpServletRequest request, HttpServletResponse httpServletResponse){
        return  dataService.musicService(request,httpServletResponse);
    }
    @GetMapping("/musicone/{idname}")
    public  String  musicone(@PathVariable("idname") String s, Map<String,Object> map){
         map.put("id",s);
         map.put("url","http://h370o35614.zicp.vip/play/"+s);
         return  "music_one";
    }

}
