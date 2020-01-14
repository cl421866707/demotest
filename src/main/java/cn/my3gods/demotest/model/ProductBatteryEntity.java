/**
 * WingSing CONFIDENTIAL
 * _____________________
 * <p>
 * [2014] - [2015] WingSing Supply Chain Management Co. (Shenzhen) Ltd.
 * All Rights Reserved.
 * <p>
 * NOTICE: All information contained herein is, and remains the property of
 * WingSing Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary
 * to WingSing Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers and
 * may be covered by China and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law. Dissemination of this information
 * or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from WingSing Supply Chain Management Co. (Shenzhen)
 * Ltd.
 */

package cn.my3gods.demotest.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 货品电池
 * 
 * @author
 * @date 2019/01/03
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class ProductBatteryEntity implements Serializable {
	private static final long serialVersionUID = 2973110187954301849L;
	private Integer id;// int
	private Integer productId;
	private String code;//编码
	private String type;//类型
	private String name;//电池名称
	private String section;//电池容量/重量区间段编号
	private Double groupCapacity;//电池组容量
	private Double coreCapacity;//电池芯容量
	private Double groupWeight;//电池组锂含量
	private Double coreWeight;//电池芯锂含量
	private Double weight;//净重量
	private Integer batteryNum;//电池数量
	private Boolean containBatteryMsg; // 是否保存有电池附加信息
}