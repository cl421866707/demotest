package cn.my3gods.demotest.dto.bc.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Auther: Charlie
 * @Date: 2020/4/28 11:00
 * @Description: BigCommerce对应OMS系统所需的产品参数
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BcProductDto {

    // BigCommerce的product的ID
    @JsonProperty(value = "id", access = Access.READ_WRITE)
    private Integer bcId;

    // 品牌ID
    @JsonProperty(value = "brand_id", access = Access.READ_WRITE)
    private Integer brandId;

    // producr名称
    private String name;

    // sku
    @JsonProperty(value = "sku", access = Access.READ_WRITE)
    private String skuCode;

    // 描述,可为html文本
    private String description;

    // 重量 TODO 单位暂时不清楚
    private Double weight;

    // 高度 TODO 单位暂时不清楚
    private Double height;

    // 宽度 TODO 单位暂时不清楚
    private Double width;

    // 深度 TODO (长度?)单位暂时不清楚
    @JsonProperty(value = "depth", access = Access.READ_WRITE)
    private Double length;

    // 商店设置的产品价格 TODO 货币单位不清楚
    private String price;

    // TODO BigCommerce 中无对应字段，此处先写死
    private String currency = "EUR";

    /*
    原JSON：
{
    "id": 94,
    "name": "[Sample] Oak Cheese Grater123",
    "type": "physical",
    "sku": "OCG1",
    "description": "<p>Crafted from oak and stainless steel this handy cheese grater is the perfect addition for the considered tabletop. Works wonderfully for grated parmesan: the fine stainless steel teeth grate the thing strings of cheese into the drawer below allowing guests to pinch as needed. </p>\n<p>Measures 14 x 8.6 x 8.9 cm / 5.5 x <span>3.38</span> x 3.5 in</p>",
    "weight": 1,
    "width": 0,
    "depth": 0,
    "height": 0,
    "price": 34.95,
    "cost_price": 0,
    "retail_price": 0,
    "sale_price": 0,
    "map_price": 0,
    "tax_class_id": 0,
    "product_tax_code": "",
    "calculated_price": 34.95,
    "categories": [
        21,
        23
    ],
    "brand_id": 35,
    "option_set_id": null,
    "option_set_display": "right",
    "inventory_level": 0,
    "inventory_warning_level": 0,
    "inventory_tracking": "none",
    "reviews_rating_sum": 0,
    "reviews_count": 0,
    "total_sold": 2,
    "fixed_cost_shipping_price": 0,
    "is_free_shipping": false,
    "is_visible": true,
    "is_featured": false,
    "related_products": [
        -1
    ],
    "warranty": "",
    "bin_picking_number": "0",
    "layout_file": "product.html",
    "upc": "",
    "mpn": "",
    "gtin": "",
    "search_keywords": "",
    "availability": "available",
    "availability_description": "",
    "gift_wrapping_options_type": "any",
    "gift_wrapping_options_list": [],
    "sort_order": 0,
    "condition": "New",
    "is_condition_shown": false,
    "order_quantity_minimum": 0,
    "order_quantity_maximum": 0,
    "page_title": "",
    "meta_keywords": [],
    "meta_description": "",
    "date_created": "2015-07-03T18:31:23+00:00",
    "date_modified": "2020-04-28T02:26:35+00:00",
    "view_count": 9,
    "preorder_release_date": null,
    "preorder_message": "",
    "is_preorder_only": false,
    "is_price_hidden": false,
    "price_hidden_label": "",
    "custom_url": {
        "url": "/oak-cheese-grater/",
        "is_customized": false
    },
    "base_variant_id": 68,
    "open_graph_type": "product",
    "open_graph_title": "",
    "open_graph_description": "",
    "open_graph_use_meta_description": true,
    "open_graph_use_product_name": true,
    "open_graph_use_image": true,
    "variants": [
        {
            "id": 68,
            "product_id": 94,
            "sku": "OCG1",
            "sku_id": null,
            "price": 34.95,
            "calculated_price": 34.95,
            "sale_price": 0,
            "retail_price": 0,
            "map_price": 0,
            "weight": 1,
            "width": 0,
            "height": 0,
            "depth": 0,
            "is_free_shipping": false,
            "fixed_cost_shipping_price": 0,
            "calculated_weight": 1,
            "purchasing_disabled": false,
            "purchasing_disabled_message": "",
            "image_url": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/314/oakcheesegrater2.1435948253.386.513.jpg?c=1",
            "cost_price": 0,
            "upc": "",
            "mpn": "",
            "gtin": "",
            "inventory_level": 0,
            "inventory_warning_level": 0,
            "bin_picking_number": "0",
            "option_values": []
        }
    ],
    "images": [
        {
            "id": 315,
            "product_id": 94,
            "is_thumbnail": false,
            "sort_order": 2,
            "description": "",
            "image_file": "%%SAMPLE%%/stencil/oakcheesegrater1.jpg",
            "url_zoom": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/315/oakcheesegrater1.1435948253.1280.1280.jpg?c=1",
            "url_standard": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/315/oakcheesegrater1.1435948253.386.513.jpg?c=1",
            "url_thumbnail": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/315/oakcheesegrater1.1435948253.220.290.jpg?c=1",
            "url_tiny": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/315/oakcheesegrater1.1435948253.44.58.jpg?c=1",
            "date_modified": "2020-04-27T09:27:46+00:00"
        },
        {
            "id": 316,
            "product_id": 94,
            "is_thumbnail": false,
            "sort_order": 1,
            "description": "",
            "image_file": "%%SAMPLE%%/stencil/oakcheesegrater3.jpg",
            "url_zoom": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/316/oakcheesegrater3.1435948254.1280.1280.jpg?c=1",
            "url_standard": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/316/oakcheesegrater3.1435948254.386.513.jpg?c=1",
            "url_thumbnail": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/316/oakcheesegrater3.1435948254.220.290.jpg?c=1",
            "url_tiny": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/316/oakcheesegrater3.1435948254.44.58.jpg?c=1",
            "date_modified": "2020-04-27T09:27:46+00:00"
        },
        {
            "id": 317,
            "product_id": 94,
            "is_thumbnail": false,
            "sort_order": 3,
            "description": "",
            "image_file": "%%SAMPLE%%/stencil/oakcheesegrater4.jpg",
            "url_zoom": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/317/oakcheesegrater4.1435948254.1280.1280.jpg?c=1",
            "url_standard": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/317/oakcheesegrater4.1435948254.386.513.jpg?c=1",
            "url_thumbnail": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/317/oakcheesegrater4.1435948254.220.290.jpg?c=1",
            "url_tiny": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/317/oakcheesegrater4.1435948254.44.58.jpg?c=1",
            "date_modified": "2020-04-27T09:27:46+00:00"
        },
        {
            "id": 314,
            "product_id": 94,
            "is_thumbnail": true,
            "sort_order": 0,
            "description": "",
            "image_file": "%%SAMPLE%%/stencil/oakcheesegrater2.jpg",
            "url_zoom": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/314/oakcheesegrater2.1435948253.1280.1280.jpg?c=1",
            "url_standard": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/314/oakcheesegrater2.1435948253.386.513.jpg?c=1",
            "url_thumbnail": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/314/oakcheesegrater2.1435948253.220.290.jpg?c=1",
            "url_tiny": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/314/oakcheesegrater2.1435948253.44.58.jpg?c=1",
            "date_modified": "2020-04-27T09:27:46+00:00"
        }
    ],
    "primary_image": {
        "id": 314,
        "product_id": 94,
        "is_thumbnail": true,
        "sort_order": 0,
        "description": "",
        "image_file": "%%SAMPLE%%/stencil/oakcheesegrater2.jpg",
        "url_zoom": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/314/oakcheesegrater2.1435948253.1280.1280.jpg?c=1",
        "url_standard": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/314/oakcheesegrater2.1435948253.386.513.jpg?c=1",
        "url_thumbnail": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/314/oakcheesegrater2.1435948253.220.290.jpg?c=1",
        "url_tiny": "https://cdn11.bigcommerce.com/s-l4oraug7n7/products/94/images/314/oakcheesegrater2.1435948253.44.58.jpg?c=1",
        "date_modified": "2020-04-27T09:27:46+00:00"
    },
    "videos": [],
    "custom_fields": [],
    "bulk_pricing_rules": [],
    "options": [],
    "modifiers": []
}
     */
}
