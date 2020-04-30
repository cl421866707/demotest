package cn.my3gods.demotest.controller;

import cn.my3gods.demotest.constants.BcCatalogModeConstant;
import cn.my3gods.demotest.dto.bc.BcBaseQo;
import cn.my3gods.demotest.dto.bc.brand.BcBrandDto;
import cn.my3gods.demotest.dto.bc.product.BcProductDto;
import cn.my3gods.demotest.util.GenericMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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
        if (Objects.nonNull(baseUrl)) {
            return baseUrl;
        }
        baseUrl = bigCommerceBaseUrl + "/" + BcCatalogModeConstant.STORE_HASH + "/" + targetMode;
        return baseUrl;
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
    public List<BcBrandDto> getBrandsByCondition(BcBaseQo baseQo) throws IllegalAccessException {
        log.debug("URI参数：{}", baseQo);
        String url = GenericMethod.addUrlParams(getBaseUrl(BcCatalogModeConstant.BRAND), baseQo);
        log.debug("请求URL：{}", url);

        String result = bcRestTemplate.getForObject(url, String.class, baseQo);
        log.debug("请求结果：{}", result);
        List<BcBrandDto> bcBrandDtos = GenericMethod.parseResponseData(result, new TypeReference<List<BcBrandDto>>() {
        });
        if (CollectionUtils.isNotEmpty(bcBrandDtos)) {
            bcBrandDtos.forEach(System.err::println);
        }
        return bcBrandDtos;
    }

    @GetMapping("products")
    public List<BcProductDto> getProductsWithoutCondition() {
        String url = getBaseUrl(BcCatalogModeConstant.PRODUCT);
        log.debug("请求URL：{}", url);
        String result = bcRestTemplate.getForObject(url, String.class);
        log.debug("请求结果：{}", result);
        List<BcProductDto> bcProductDtos = GenericMethod.parseResponseData(result, new TypeReference<List<BcProductDto>>() {
        });
        if (CollectionUtils.isNotEmpty(bcProductDtos)) {
            bcProductDtos.forEach(System.err::println);
        }
        return bcProductDtos;
    }
}
