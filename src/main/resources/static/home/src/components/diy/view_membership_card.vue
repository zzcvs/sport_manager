<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce">
							<el-col v-if="$check_field('get','membership_card_name') || $check_field('add','membership_card_name') || $check_field('set','membership_card_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="会员卡名称" prop="membership_card_name">
												<el-input id="membership_card_name" v-model="form['membership_card_name']" placeholder="请输入会员卡名称"
							  v-if="(form['membership_card_id'] && $check_field('set','membership_card_name')) || (!form['membership_card_id'] && $check_field('add','membership_card_name'))" :disabled="disabledObj['membership_card_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','membership_card_name')">{{form['membership_card_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','membership_card_type') || $check_field('add','membership_card_type') || $check_field('set','membership_card_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="会员卡类型" prop="membership_card_type">
												<el-input id="membership_card_type" v-model="form['membership_card_type']" placeholder="请输入会员卡类型"
							  v-if="(form['membership_card_id'] && $check_field('set','membership_card_type')) || (!form['membership_card_id'] && $check_field('add','membership_card_type'))" :disabled="disabledObj['membership_card_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','membership_card_type')">{{form['membership_card_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','membership_card_expiration_date') || $check_field('add','membership_card_expiration_date') || $check_field('set','membership_card_expiration_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="会员卡时效" prop="membership_card_expiration_date">
												<el-input id="membership_card_expiration_date" v-model="form['membership_card_expiration_date']" placeholder="请输入会员卡时效"
							  v-if="(form['membership_card_id'] && $check_field('set','membership_card_expiration_date')) || (!form['membership_card_id'] && $check_field('add','membership_card_expiration_date'))" :disabled="disabledObj['membership_card_expiration_date_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','membership_card_expiration_date')">{{form['membership_card_expiration_date']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','membership_card_fees') || $check_field('add','membership_card_fees') || $check_field('set','membership_card_fees')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="会员卡费用" prop="membership_card_fees">
								<el-input-number id="membership_card_fees" v-model.number="form['membership_card_fees']"
						v-if="(form['membership_card_id'] && $check_field('set','membership_card_fees')) || (!form['membership_card_id'] && $check_field('add','membership_card_fees'))" :disabled="disabledObj['membership_card_fees_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','membership_card_fees')">{{form['membership_card_fees']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','membership_card_discounts') || $check_field('add','membership_card_discounts') || $check_field('set','membership_card_discounts')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="会员卡优惠" prop="membership_card_discounts">
												<el-input id="membership_card_discounts" v-model="form['membership_card_discounts']" placeholder="请输入会员卡优惠"
							  v-if="(form['membership_card_id'] && $check_field('set','membership_card_discounts')) || (!form['membership_card_id'] && $check_field('add','membership_card_discounts'))" :disabled="disabledObj['membership_card_discounts_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','membership_card_discounts')">{{form['membership_card_discounts']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','membership_card_introduction') || $check_field('add','membership_card_introduction') || $check_field('set','membership_card_introduction')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="会员卡介绍" prop="membership_card_introduction">
								<el-input type="textarea" id="membership_card_introduction" v-model="form['membership_card_introduction']" placeholder="请输入会员卡介绍"
						v-if="(form['membership_card_id'] && $check_field('set','membership_card_introduction')) || (!form['membership_card_id'] && $check_field('add','membership_card_introduction'))" :disabled="disabledObj['membership_card_introduction_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','membership_card_introduction')">{{form['membership_card_introduction']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','membership_card_details') || $check_field('add','membership_card_details') || $check_field('set','membership_card_details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="会员卡详情" prop="membership_card_details">
					<quill-editor v-model.number="form['membership_card_details']"
						v-if="(form['membership_card_id'] && $check_field('set','membership_card_details')) || (!form['membership_card_id'] && $check_field('add','membership_card_details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','membership_card_details')" v-html="form['membership_card_details']"></div>
				</el-form-item>
			</el-col>
						
	
	
		
		
	
	
	
	
	</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/membership_card/view','set') || $check_action('/membership_card/view','add') || $check_option('/membership_card/table','examine')">
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
				<el-form-item v-else>
					<el-button @click="cancel()">返回</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "membership_card_id",
				url_add: "~/api/membership_card/add?",
				url_set: "~/api/membership_card/set?",
				url_get_obj: "~/api/membership_card/get_obj?",
				url_upload: "~/api/membership_card/upload?",

				query: {
					"membership_card_id": 0,
				},

				form: {
								"membership_card_name":  '', // 会员卡名称
										"membership_card_type":  '', // 会员卡类型
										"membership_card_expiration_date":  '', // 会员卡时效
										"membership_card_fees":  0, // 会员卡费用
										"membership_card_discounts":  '', // 会员卡优惠
										"membership_card_introduction":  '', // 会员卡介绍
										"membership_card_details":  '', // 会员卡详情
											"membership_card_id": 0, // ID
													},
				disabledObj:{
								"membership_card_name_isDisabled": false,
										"membership_card_type_isDisabled": false,
										"membership_card_expiration_date_isDisabled": false,
					          			"membership_card_fees_isDisabled": false,
										"membership_card_discounts_isDisabled": false,
										"membership_card_introduction_isDisabled": false,
										"membership_card_details_isDisabled": false,
										},

	
				
				
				
				
				
				
			
			}
		},
		methods: {

	
	
			
	
			
	
			
	
			
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
																	if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
								this.form[key] = form[dbKey]
							}
							if(dbKey === "source_table"){
								this.form['source_table'] = form[dbKey];
							}
							if(dbKey === "source_id"){
								this.form['source_id'] = form[dbKey];
							}
							if(dbKey === "source_user_id"){
								this.form['source_user_id'] = form[dbKey];
							}
						})
					})
				}
																		$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

																																			

			},

																																																							async submit(param, func){
				if (!param) {
					param = this.form;
				}
								var pm = this.events("submit_before", Object.assign({}, param)) || param;
				var msg = await this.events("submit_check", pm);
				var ret;
				if (msg) {
					this.$toast(msg, 'danger');
				} else {
																																																									ret = this.events("submit_main", pm, func);
				}
				return ret;
			},
			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
						submit_check(param) {
																																																																																							return null;
			},

			is_view(){
				// var bl = this.user_group == "管理员";
				var bl = false;

				if(!bl){
					bl = this.$check_action('/membership_card/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/membership_card/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/membership_card/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/membership_card/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/membership_card/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
																},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
	
	.img_multiple{
		overflow: hidden;
	}
	.img_multiple .img_block{
		float: left;
		margin-right: 5px;
		margin-bottom: 5px;
		position: relative;
	}
	.img_multiple .img_block img{
		height: 100px;
		width: auto;
	}
	.img_multiple .img_del{
		position: absolute;
		top: 5px;
		right: 5px;
		width: 20px;
		height: 20px;
		background: #0000008a;
		color: #fff;
		line-height: 20px;
		text-align: center;
		border-radius: 100%;
		cursor: pointer;
	}




</style>
