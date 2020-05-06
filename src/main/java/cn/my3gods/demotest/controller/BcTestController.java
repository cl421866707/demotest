package cn.my3gods.demotest.controller;

import cn.my3gods.demotest.constants.BcCatalogModeConstant;
import cn.my3gods.demotest.dto.BaseResponseDto;
import cn.my3gods.demotest.dto.bc.BcBaseQo;
import cn.my3gods.demotest.dto.bc.brand.BcBrandDto;
import cn.my3gods.demotest.dto.bc.product.BcProductDto;
import cn.my3gods.demotest.util.GenericMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <br></br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/4/30 14:52
 */
@Slf4j
@RestController
@RequestMapping("bigcommerce/")
public class BcTestController {

    @Value("${bigcommerce.api.url:https://api.bigcommerce.com/stores}")
    private String bigCommerceBaseUrl = "https://api.bigcommerce.com/stores";

    private String baseUrl;

    @Resource(name = "bcRestTemplate")
    private RestTemplate bcRestTemplate;

    /**
     * 获取对应模块的基础URL
     *
     * @param targetMode 基础模块URL路径
     * @return 完整URL路径
     */
    private String getBaseUrl(String targetMode) {
        return bigCommerceBaseUrl + "/" + BcCatalogModeConstant.STORE_HASH + "/" + targetMode;
    }

//    @GetMapping("brands")
//    public List<BcBrandDto> getBrandsWithoutCondition() {
//        String finalUrl = getBaseUrl(BcCatalogModeConstant.BRAND);
//        log.debug("请求URL：{}", finalUrl);
//        String result = bcRestTemplate.getForObject(finalUrl, String.class);
//        log.debug("请求结果：{}", result);
//        List<BcBrandDto> bcBrandDtos = GenericMethod.parseResponseData(result, new TypeReference<List<BcBrandDto>>() {
//        });
//        if (CollectionUtils.isNotEmpty(bcBrandDtos)) {
//            bcBrandDtos.forEach(System.err::println);
//        }
//        return bcBrandDtos;
//    }

    @GetMapping("brands")
    public BaseResponseDto<List<BcBrandDto>> getBrandsByCondition(BcBaseQo baseQo) throws IllegalAccessException {
        log.debug("URI参数：{}", baseQo);
        String url = GenericMethod.addUrlParams(getBaseUrl(BcCatalogModeConstant.BRAND), baseQo);
        log.debug("请求URL：{}", url);

        String result = this.get(url);
        log.debug("请求结果：{}", result);
        if (StringUtils.isBlank(result)) {
            return BaseResponseDto.fail("request failed url:" + url);
        }
        List<BcBrandDto> bcBrandDtos = GenericMethod.parseResponseData(result, new TypeReference<List<BcBrandDto>>() {
        });
        if (CollectionUtils.isNotEmpty(bcBrandDtos)) {
            bcBrandDtos.forEach(System.err::println);
        }
        return BaseResponseDto.success(bcBrandDtos);
    }

    @GetMapping("brands/{brandId}")
    public BaseResponseDto<BcBrandDto> getBrandById(@PathVariable String brandId, BcBaseQo baseQo) throws IllegalAccessException {
        log.debug("URI参数：{}", baseQo);
        String url = GenericMethod.addPathVariableAndUrlParams(getBaseUrl(BcCatalogModeConstant.BRAND), Collections.singletonList(brandId), baseQo);
        String result = this.get(url);
        log.debug("请求结果：{}", result);
        if (StringUtils.isBlank(result)) {
            return BaseResponseDto.fail("request failed url:" + url);
        }
        BcBrandDto bcBrandDto = GenericMethod.parseResponseData(result, new TypeReference<BcBrandDto>() {
        });
        return BaseResponseDto.success(bcBrandDto);
    }

    @GetMapping("products")
    public BaseResponseDto<List<BcProductDto>> getProductsByCondition(BcBaseQo baseQo) throws IllegalAccessException {
        String url = GenericMethod.addUrlParams(getBaseUrl(BcCatalogModeConstant.PRODUCT), baseQo);
        String result = this.get(url);
        log.debug("请求结果：{}", result);
        if (StringUtils.isBlank(result)) {
            return BaseResponseDto.fail("request failed url:" + url);
        }
        List<BcProductDto> bcProductDtos = GenericMethod.parseResponseData(result, new TypeReference<List<BcProductDto>>() {
        });
        if (CollectionUtils.isNotEmpty(bcProductDtos)) {
            bcProductDtos.forEach(System.err::println);
        }
        return BaseResponseDto.success(bcProductDtos);
    }

    @GetMapping("products/{productId}")
    public BaseResponseDto<BcProductDto> getProductById(@PathVariable String productId, BcBaseQo baseQo) throws IllegalAccessException {
        log.debug("URI参数：{}", baseQo);
        String url = GenericMethod.addPathVariableAndUrlParams(getBaseUrl(BcCatalogModeConstant.PRODUCT), Collections.singletonList(productId), baseQo);
        String result = this.get(url);
        log.debug("请求结果：{}", result);
        if (StringUtils.isBlank(result)) {
            return BaseResponseDto.fail("request failed url:" + url);
        }
        BcProductDto bcProductDto = GenericMethod.parseResponseData(result, new TypeReference<BcProductDto>() {
        });
        return BaseResponseDto.success(bcProductDto);
    }

    private String get(String url) {
        log.debug("请求URL：{}", url);
        ResponseEntity<String> responseEntity = this.bcRestTemplate.getForEntity(url, String.class);
        HttpHeaders headers = responseEntity.getHeaders();
        HttpStatus statusCode = responseEntity.getStatusCode();
        log.info("get http headers:{}", headers);
        log.info("get http status:{}", statusCode);
        return statusCode.is2xxSuccessful() ? responseEntity.getBody() : null;
    }
}
