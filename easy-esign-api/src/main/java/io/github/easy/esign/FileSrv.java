package io.github.easy.esign;

import io.github.easy.esign.error.ESignException;
import io.github.easy.esign.struct.ESignResp;
import io.github.easy.esign.struct.file.req.FileUrlReq;
import io.github.easy.esign.struct.file.req.KeywordsReq;
import io.github.easy.esign.struct.file.resp.FileResp;
import io.github.easy.esign.struct.file.resp.FileUpResp;
import io.github.easy.esign.struct.file.resp.FileUrlResp;
import io.github.easy.esign.struct.file.resp.KeywordsResp;
import io.github.easy.esign.utils.DigestUtil;
import io.github.easy.esign.utils.JsonUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static io.github.easy.esign.constant.ContentType.*;

/**
 * 文件&模板
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FileSrv extends AbstractSrv {

    private static FileSrv instance;

    @Synchronized
    public static FileSrv getInstance() {
        if (instance == null) {
            instance = new FileSrv();
        }
        return instance;
    }


    /**
     * 上传本地文件step1
     */
    public ESignResp<FileUrlResp> fileUploadUrl(FileUrlReq request, File file) {
        //step1
        String path = "/v3/files/file-upload-url";
        try {
            request.setContentMd5(DigestUtil.getStringContentMd5(file));
        } catch (IOException e) {
            throw new ESignException(e);
        }
        request.setContentType(PDF_CT);
        return execute().post(path, request, FileUrlResp.class);
    }

    /**
     * 上传本地文件step2
     */
    public FileUpResp fileUpload(String fileUploadUrl, File file) throws IOException {
        MediaType mediaType = MediaType.parse(PDF_CT);
        RequestBody requestBody = RequestBody.Companion.create(file, mediaType);
        Request request = new Request.Builder()
                .url(fileUploadUrl)
                .put(requestBody)
                .header("Content-MD5", DigestUtil.getStringContentMd5(file))
                .header("Content-Type", JSON_CT)
                .build();
        OkHttpClient httpClient = execute().getHttpClient();
        try (Response r = httpClient.newCall(request).execute()) {
            String text = Objects.requireNonNull(r.body()).string();
            return JsonUtil.parseObject(text, FileUpResp.class);
        } catch (IOException e) {
            throw new ESignException(e);
        }
    }

    /**
     * 查询文件上传状态
     */
    public ESignResp<FileResp> get(String fileId) {
        String path = "/v3/file/" + fileId;
        return execute().get(path, FileResp.class);
    }

    /**
     * 检索文件关键字坐标
     */
    public ESignResp<KeywordsResp> keywordPositions(String fileId, KeywordsReq keywords) {
        String path = String.format("/v3/files/%s/keyword-positions", fileId);
        return execute().post(path, keywords, KeywordsResp.class);
    }

}
