<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce">
							<el-col v-if="$check_field('get','report_name') || $check_field('add','report_name') || $check_field('set','report_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="报表名称" prop="report_name">
												<el-input id="report_name" v-model="form['report_name']" placeholder="请输入报表名称"
							  v-if="(form['financial_statements_id'] && $check_field('set','report_name')) || (!form['financial_statements_id'] && $check_field('add','report_name'))" :disabled="disabledObj['report_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','report_name')">{{form['report_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','report_type') || $check_field('add','report_type') || $check_field('set','report_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="报表类型" prop="report_type">
												<el-input id="report_type" v-model="form['report_type']" placeholder="请输入报表类型"
							  v-if="(form['financial_statements_id'] && $check_field('set','report_type')) || (!form['financial_statements_id'] && $check_field('add','report_type'))" :disabled="disabledObj['report_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','report_type')">{{form['report_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','statistical_date') || $check_field('add','statistical_date') || $check_field('set','statistical_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="统计日期" prop="statistical_date">
								<el-date-picker :disabled="disabledObj['statistical_date_isDisabled']" v-if="(form['financial_statements_id'] && $check_field('set','statistical_date')) || (!form['financial_statements_id'] && $check_field('add','statistical_date'))" id="statistical_date"
						v-model="form['statistical_date']" type="date" placeholder="选择日期" value-format="yyyy-MM-dd">
					</el-date-picker>
					<div v-else-if="$check_field('get','statistical_date')">{{form['statistical_date']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','statistical_cycle') || $check_field('add','statistical_cycle') || $check_field('set','statistical_cycle')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="统计周期" prop="statistical_cycle">
												<el-input id="statistical_cycle" v-model="form['statistical_cycle']" placeholder="请输入统计周期"
							  v-if="(form['financial_statements_id'] && $check_field('set','statistical_cycle')) || (!form['financial_statements_id'] && $check_field('add','statistical_cycle'))" :disabled="disabledObj['statistical_cycle_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','statistical_cycle')">{{form['statistical_cycle']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','statistical_amount') || $check_field('add','statistical_amount') || $check_field('set','statistical_amount')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="统计金额" prop="statistical_amount">
								<el-input-number id="statistical_amount" v-model.number="form['statistical_amount']"
						v-if="(form['financial_statements_id'] && $check_field('set','statistical_amount')) || (!form['financial_statements_id'] && $check_field('add','statistical_amount'))" :disabled="disabledObj['statistical_amount_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','statistical_amount')">{{form['statistical_amount']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','statement_details') || $check_field('add','statement_details') || $check_field('set','statement_details')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="报表详情" prop="statement_details">
								<el-input type="textarea" id="statement_details" v-model="form['statement_details']" placeholder="请输入报表详情"
						v-if="(form['financial_statements_id'] && $check_field('set','statement_details')) || (!form['financial_statements_id'] && $check_field('add','statement_details'))" :disabled="disabledObj['statement_details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','statement_details')">{{form['statement_details']}}</div>
							</el-form-item>
			</el-col>
						
	
	
		
		
	
	
	
	
	</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/financial_statements/view','set') || $check_action('/financial_statements/view','add') || $check_option('/financial_statements/table','examine')">
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
				field: "financial_statements_id",
				url_add: "~/api/financial_statements/add?",
				url_set: "~/api/financial_statements/set?",
				url_get_obj: "~/api/financial_statements/get_obj?",
				url_upload: "~/api/financial_statements/upload?",

				query: {
					"financial_statements_id": 0,
				},

				form: {
								"report_name":  '', // 报表名称
										"report_type":  '', // 报表类型
										"statistical_date":  '', // 统计日期
										"statistical_cycle":  '', // 统计周期
										"statistical_amount":  0, // 统计金额
										"statement_details":  '', // 报表详情
											"financial_statements_id": 0, // ID
													},
				disabledObj:{
								"report_name_isDisabled": false,
										"report_type_isDisabled": false,
										"statistical_date_isDisabled": false,
										"statistical_cycle_isDisabled": false,
					          			"statistical_amount_isDisabled": false,
										"statement_details_isDisabled": false,
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
						        if (this.form["statistical_date"] && this.form["statistical_date"].indexOf("-")===-1){
          this.form["statistical_date"] = this.$toTime(parseInt(this.form["statistical_date"]),"yyyy-MM-dd")
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

																if(json.result.obj["statistical_date"]=="0000-00-00"){
				  json.result.obj["statistical_date"] = null;
				}
				if(parseInt(json.result.obj["statistical_date"]) > 9999){
					json.result.obj["statistical_date"] = this.$toTime(parseInt(json.result.obj["statistical_date"]),"yyyy-MM-dd")
				}
																			

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
																																	if (!param.statistical_date){
					return "统计日期不能为空";
				}
																																																	return null;
			},

			is_view(){
				// var bl = this.user_group == "管理员";
				var bl = false;

				if(!bl){
					bl = this.$check_action('/financial_statements/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/financial_statements/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/financial_statements/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/financial_statements/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/financial_statements/view','get');
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
