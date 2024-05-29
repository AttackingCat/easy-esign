package io.github.easy.esign.api;

import io.github.easy.esign.core.ESignManager;
import io.github.easy.esign.core.error.ESignExecution;
import io.github.easy.esign.core.log.Logger;
import io.github.easy.esign.core.log.LoggerFactory;
import io.github.easy.esign.struct.file.resp.FileResp;
import io.github.easy.esign.struct.file.req.FileUrlReq;
import io.github.easy.esign.struct.file.req.KeywordsReq;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.file.resp.FileUpResp;
import io.github.easy.esign.struct.file.resp.FileUrlResp;
import io.github.easy.esign.struct.file.resp.KeywordsResp;
import io.github.easy.esign.utils.DigestUtil;
import io.github.easy.esign.utils.JsonUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static io.github.easy.esign.api.BaseHandler.CONTENT_TYPE;
import static io.github.easy.esign.api.BaseHandler.PDF_CONTENT_TYPE;


/**
 * 文件&模板
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ESignFileSrv {
    private static volatile ESignFileSrv INSTANCE;

    public static synchronized ESignFileSrv getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ESignFileSrv();
        }
        return INSTANCE;
    }

    private static final Logger log = LoggerFactory.getLogger(ESignFileSrv.class);

    /**
     * 上传本地文件1
     */
    public ESignResp<FileUrlResp> fileUploadUrl(FileUrlReq request, File file) {
        //step1
        String path = "/v3/files/file-upload-url";
        try {
            request.setContentMd5(DigestUtil.getStringContentMd5(file));
        } catch (NoSuchAlgorithmException | IOException e) {
            log.error("GetStringContentMd5 error");
            throw new ESignExecution(e);
        }
        request.setContentType(PDF_CONTENT_TYPE);
        return ESignManager.getContext().post(path, request, FileUrlResp.class);
    }

    /**
     * 上传本地文件2
     */
    public FileUpResp fileUpload(String fileUploadUrl, File file) throws IOException {
        RequestBody requestBody = RequestBody.create(MediaType.parse(PDF_CONTENT_TYPE), file);
        Request request;
        try {
            request = new Request.Builder()
                    .url(fileUploadUrl)
                    .put(requestBody)
                    .header("Content-MD5", DigestUtil.getStringContentMd5(file))
                    .header("Content-Type", CONTENT_TYPE)
                    .build();
        } catch (NoSuchAlgorithmException e) {
            log.error("GetStringContentMd5 error");
            throw new ESignExecution(e);
        }
        OkHttpClient httpClient = ESignManager.getContext().getHttpClient();
        Response r = httpClient.newCall(request).execute();
        String text = Objects.requireNonNull(r.body()).string();
        return JsonUtil.parseObject(text, FileUpResp.class);
    }

    /**
     * 查询文件上传状态
     */
    public ESignResp<FileResp> get(String fileId) {
        String path = "/v3/file/" + fileId;
        return ESignManager.getContext().get(path, FileResp.class);
    }

    /**
     * 检索文件关键字坐标
     */
    public ESignResp<KeywordsResp> keywordPositions(String fileId, KeywordsReq keywords) {
        String path = String.format("/v3/files/%s/keyword-positions", fileId);
        return ESignManager.getContext().post(path, keywords, KeywordsResp.class);
    }

}
