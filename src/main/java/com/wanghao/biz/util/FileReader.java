package com.wanghao.biz.util;

import com.wanghao.biz.err.BusinessException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author wanghao
 */
public class FileReader {
    private static final Logger logger = LoggerFactory.getLogger(FileReader.class);

    private InputStream in;
    private int readSize;

    public FileReader(String flvPath) {
        checkAndInit(flvPath);
    }

    private void checkAndInit(String flvPath) throws BusinessException {
        if (flvPath == null) {
            throw new BusinessException("flv path invalid");
        }
        if (!flvPath.endsWith(Constant.FLV_SUFFIX)) {
            throw new BusinessException("should end with .flv");
        }
        File file = new File(flvPath);
        if (!file.exists()) {
            throw new BusinessException("file not exist");
        }
        if (!file.isFile()) {
            throw new BusinessException("not a file");
        }
        if (!file.canRead()) {
            throw new BusinessException("no permission to read");
        }
        if (file.length() > Constant.MAX_FILE_SIZE) {
            logger.info("exceed max file size: {} bytes",  Constant.MAX_FILE_SIZE);
            readSize = Constant.MAX_FILE_SIZE;
        } else {
            readSize = (int) file.length();
        }

        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new BusinessException("file not exist");
        }
    }

    public ByteBuf read() throws IOException {
        byte[] bytes = new byte[readSize];
        int len = in.read(bytes);
        return Unpooled.copiedBuffer(bytes);
    }
}
