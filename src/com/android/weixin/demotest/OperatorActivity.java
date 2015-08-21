package com.android.weixin.demotest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 有符号运算：符号位参与运算规则，但转换为十进制时符号位不进行计算值。
 * 
 * 无符号运算：符号位参与运算规则，但转换为十进制时符号位将进行计算值。
 * 
 * 位移时是连符号一起位移的，无符号右位移后将会将符号位一起计算为值。有符号位移则符号位将不计算为值
 * 
 * 按位运算符：0为假,1为真。按位运算符是有符号的运算符
 * 
 * @author weixin
 * 
 */
public class OperatorActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.operator_layout);
	}

	/**
	 * 左位移
	 * 
	 * 向左移动位数，可以挤掉符号位。
	 */
	private void signedLeftMove() {
		// 10的二进制数中1010
		int i = 10 << 29;
		// 向右移动29位数则为 01000000000000000000000000000000
		int i1 = 10 << 30;
		// 向右移动29位数则为 10000000000000000000000000000000
		// 该值将变为负数
		double ii = Math.pow(2, 30);
		// 所以 i会等于ii
	}

	/**
	 * 有符号右位移
	 */
	private void signedRightMove() {
		// 右移一位等于除于2，两位等于除于4
		int i = 10 >> 1;
	}

	/**
	 * 无符号右位移
	 */
	private void unsignedRightMove() {
		// 00000000000000000000000000001010
		// 00000000000000000000000000000101
		int i = 10 >>> 1;

		// -10 的二进制数为11111111111111111111111111110110
		// 无符号右位移后为00000000000000000000000000000011 等于 3
		int ii = -10 >>> 30;
	}

	/**
	 * 按位运算符：与("&")
	 * 
	 * 两个操作数中位都为1，结果才为1，否则结果为0
	 */
	private void withOperator() {
		int a = 129;
		int b = 128;
		Log.i("--->", "a 和b 与的结果是：" + (a & b));
		// 129的二进制为10000001，128的二进制为10000000
		// 根据与运算符的运算规律，只有两个位都是1，结果才是1，可以知道结果就是10000000，即128
	}

	/**
	 * 按位运算符：或("|")
	 * 
	 * 两个位只要有一个为1，那么结果就是1，否则就为0
	 */
	private void orOperator() {
		int a = 129;
		int b = 128;
		Log.i("--->", "a 和b 与的结果是：" + (a | b));
		// 129的二进制为10000001，128的二进制为10000000
		// 根据或运算符的运算规律，只有两个位有一个是1，结果才是1，可以知道结果就是10000001，即129
	}

	/**
	 * 按位运算符：非("~")
	 * 
	 * 如果位为0，结果是1，如果位为1，结果是0
	 */
	private void notOperator() {
		int a = 129;
		Log.i("--->", "a 非的结果是：" + (~a));
		// 129的二进制为10000001
		// 根据或运算符的运算规律,如果位为0，结果是1；如果位为1，结果是0。可以知道结果就是01111110
	}

	/**
	 * 按位运算符：异或("^")
	 * 
	 * 两个操作数的位中，相同则结果为0，不同则结果为1
	 */
	private void xorOperator() {
		int a = 15;
		int b = 2;
		Log.i("--->", "a 与 b 异或的结果是：" + (a ^ b));
		// a 的值是15，转换成二进制为1111，而b 的值是2，转换成二进制为0010
		// 根据异或的运算规律，可以得出其结果为1101 即13
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.signed_left_move_btn:
			signedLeftMove();
			break;
		case R.id.signed_right_move_btn:
			signedRightMove();
			break;
		case R.id.unsigned_right_move_btn:
			unsignedRightMove();
			break;
		default:
			break;
		}
	}

}
