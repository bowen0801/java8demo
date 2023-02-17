package com.videocut;

import com.google.common.collect.Lists;
import org.bytedeco.javacv.FFmpegFrameGrabber;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {


    //分割视频的大小
    private long blockSize = 1 * 1024 * 1024;

    @org.junit.Test
    public void Test1() throws Exception {
        //D:\\testdemo\\a.MP4"
        List<String> lists = cutVideo("D:/testdemo/a.MP4");
        System.out.println(lists);
    }

    /**
     * @param filePath 要处理的文件路径
     * @return 分割后的文件路径
     * @throws Exception 文件
     */
    List<String> cutVideo(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath + "文件不存在");
        }
        if (!filePath.toLowerCase().endsWith(".mp4")) {
            throw new Exception("文件格式错误");
        }
        //从ffmpeg获得的时间长度00:00:00格式
        String videoTimeString = getVideoTime(file);
        System.out.println("从ffmpeg获得的时间长度00:00:00格式:{"+videoTimeString+"}");
        //将时长转换为秒数
        int videoSecond = VideoUtils.parseTimeToSecond(videoTimeString);
        System.out.println("将时长转换为秒数:{"+videoSecond+"}");
        //视频文件的大小
        long fileLength = VideoUtils.getVideoFileLength(file);
        System.out.println("视频文件的大小:{"+fileLength+"}");
        List<String> cutedVideoPaths = new ArrayList<String>();
        if (fileLength <= blockSize) {
            System.out.println("如果视频文件大小不大于预设值，则直接返回原视频文件");
            cutedVideoPaths.add(filePath);
        } else {
            System.out.println("超过预设大小，需要切割");
            int partNum = (int) (fileLength / blockSize);
            System.out.println("文件大小除以分块大小的商:{"+partNum+"}");
            long remainSize = fileLength % blockSize;
            System.out.println("余数:{"+remainSize+"}");
            int cutNum;
            if (remainSize > 0) {
                cutNum = partNum + 1;
            } else {
                cutNum = partNum;
            }
            System.out.println("cutNum:{"+cutNum+"}");
            int eachPartTime = videoSecond / cutNum;
            System.out.println("eachPartTime:{"+eachPartTime+"}");
            String fileFolder = file.getParentFile().getAbsolutePath();
            System.out.println("fileFolder:{"+fileFolder+"}");
            String fileName[] = file.getName().split("\\.");
            System.out.println("fileName[]:{"+fileName+"}");

            for (int i = 0; i < cutNum; i++) {
                List<String> commands = Lists.newArrayList();
                commands.add("ffmpeg");
                commands.add("-ss");
                commands.add(VideoUtils.parseTimeToString(eachPartTime * i));
                if (i != cutNum - 1) {
                    commands.add("-t");
                    commands.add(VideoUtils.parseTimeToString(eachPartTime));
                }
                commands.add("-i");
                commands.add(filePath);
                commands.add("-codec");
                commands.add("copy");
                commands.add(fileFolder + File.separator + fileName[0] + "_part" + i + "." + fileName[1]);
                cutedVideoPaths.add(fileFolder + File.separator + fileName[0] + "_part" + i + "." + fileName[1]);
                newRunCommand(commands);
            }
        }
        return cutedVideoPaths;
    }

    private Result newRunCommand(List<String> command) {
        System.out.println("相关命令 command:{"+command+"}");
        Result result = new Result(false, "");
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);
        try {
            Process process = builder.start();
            final StringBuilder stringBuilder = new StringBuilder();
            final InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            while ((line = reader.readLine()) != null){

            }
            if (reader != null) {
                reader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("ffmpeg执行异常" + e.getMessage());
        }
        return result;
    }

    /**
     * 获取视频文件时长
     *
     * @param file 文件
     * @return 时长 格式hh:MM:ss
     * @throws FileNotFoundException 视频不存在抛出此异常
     */
    private String getVideoTime(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath() + "不存在");
        }
        List<String> commands = new ArrayList<>();
        commands.add("ffmpeg");
        commands.add("-i");
        commands.add(file.getAbsolutePath());
        Result result = newRunCommand(commands);
        String msg = result.getMsg();
        if (result.isSuccess()) {
            Pattern pattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
            Matcher matcher = pattern.matcher(msg);
            String time = "";
            while (matcher.find()) {
                time = matcher.group();
            }
            return time;
        } else {
            return "";
        }
    }

    /**
     * 获取视频时长，单位为秒
     * @param file 即为视频地址
     * @return 时长（秒）
     */
    private Long getVideoTime1(File file){
        Long times = 0L;
        try {
            FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
            ff.start();
            times = ff.getLengthInTime()/(1000*1000);
            ff.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }
}
