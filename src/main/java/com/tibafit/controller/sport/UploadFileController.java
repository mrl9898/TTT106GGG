package com.tibafit.controller.sport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.tibafit.dto.sport.ApiResponseDTO;
import com.tibafit.dto.sport.UploadFileResponseDTO;
import com.tibafit.model.sporttype.UploadFileFuncType;
import com.tibafit.service.sport.UploadFileService_interface;

import java.util.Map;

@RestController
@RequestMapping("/fileImg/api")
public class UploadFileController {

    @Autowired
    private UploadFileService_interface uploadFileService;

    /**
     * 上傳檔案
     */
    @PostMapping("/upload")
    public ResponseEntity<ApiResponseDTO<UploadFileResponseDTO>> uploadFile(
            @RequestParam("funcType") UploadFileFuncType funcType,
            @RequestParam("file") MultipartFile file) {
        try {
            UploadFileResponseDTO res = uploadFileService.uploadPic(funcType, file);
            return ResponseEntity.ok(ApiResponseDTO.success(res));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(ApiResponseDTO.parameterError(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponseDTO.systemError(e.getMessage()));
        }
    }

    /**
     * 刪除檔案
     */
    @PostMapping("/delete")
    public ResponseEntity<ApiResponseDTO<Boolean>> deleteFile(
            @RequestParam("funcType") UploadFileFuncType funcType,
            @RequestParam("fileName") String fileName) {
        try {
            boolean result = uploadFileService.deleteFile(funcType, fileName);
            return ResponseEntity.ok(ApiResponseDTO.success(result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(ApiResponseDTO.parameterError(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponseDTO.systemError(e.getMessage()));
        }
    }

    /**
     * 更新檔案
     */
    @PostMapping("/update")
    public ResponseEntity<ApiResponseDTO<UploadFileResponseDTO>> updateFile(
            @RequestParam("funcType") UploadFileFuncType funcType,
            @RequestParam(value = "oldFileName", required = false) String oldFileName,
            @RequestParam(value = "newFile", required = false) MultipartFile newFile) {
        try {
            UploadFileResponseDTO res = uploadFileService.updateFile(funcType, oldFileName, newFile);
            return ResponseEntity.ok(ApiResponseDTO.success(res));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(ApiResponseDTO.parameterError(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponseDTO.systemError(e.getMessage()));
        }
    }

    /**
     * 取得單一檔案資訊
     */
    @GetMapping("/getSingle")
    public ResponseEntity<ApiResponseDTO<UploadFileResponseDTO>> getSingle(
            @RequestParam("funcType") UploadFileFuncType funcType,
            @RequestParam("fileName") String fileName) {
        try {
            UploadFileResponseDTO res = uploadFileService.getFile(funcType, fileName);
            return ResponseEntity.ok(ApiResponseDTO.success(res));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(ApiResponseDTO.parameterError(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponseDTO.systemError(e.getMessage()));
        }
    }

    /**
     * 取得該功能下的所有檔案 Map <檔名, DTO>
     */
    @GetMapping("/getMultipleByFunc")
    public ResponseEntity<ApiResponseDTO<Map<String, UploadFileResponseDTO>>> getMultipleByFunc(
            @RequestParam("funcType") UploadFileFuncType funcType) {
        try {
            Map<String, UploadFileResponseDTO> fileMap = uploadFileService.listFileMap(funcType);
            return ResponseEntity.ok(ApiResponseDTO.success(fileMap));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(ApiResponseDTO.parameterError(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponseDTO.systemError(e.getMessage()));
        }
    }
}
