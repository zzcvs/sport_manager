<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce">
							<el-col v-if="$check_field('get','equipment_number') || $check_field('add','equipment_number') || $check_field('set','equipment_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="设备编号" prop="equipment_number">
												<el-input id="equipment_number" v-model="form['equipment_number']" placeholder="请输入设备编号"
							  v-if="(form['device_information_id'] && $check_field('set','equipment_number')) || (!form['device_information_id'] && $check_field('add','equipment_number'))" :disabled="disabledObj['equipment_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','equipment_number')">{{form['equipment_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','equipment_name') || $check_field('add','equipment_name') || $check_field('set','equipment_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="设备名称" prop="equipment_name">
												<el-input id="equipment_name" v-model="form['equipment_name']" placeholder="请输入设备名称"
							  v-if="(form['device_information_id'] && $check_field('set','equipment_name')) || (!form['device_information_id'] && $check_field('add','equipment_name'))" :disabled="disabledObj['equipment_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','equipment_name')">{{form['equipment_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','equipment_type') || $check_field('add','equipment_type') || $check_field('set','equipment_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="设备类型" prop="equipment_type">
												<el-input id="equipment_type" v-model="form['equipment_type']" placeholder="请输入设备类型"
							  v-if="(form['device_information_id'] && $check_field('set','equipment_type')) || (!form['device_information_id'] && $check_field('add','equipment_type'))" :disabled="disabledObj['equipment_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','equipment_type')">{{form['equipment_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','equipment_status') || $check_field('add','equipment_status') || $check_field('set','equipment_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="设备状态" prop="equipment_status">
												<el-input id="equipment_status" v-model="form['equipment_status']" placeholder="请输入设备状态"
							  v-if="(form['device_information_id'] && $check_field('set','equipment_status')) || (!form['device_information_id'] && $check_field('add','equipment_status'))" :disabled="disabledObj['equipment_status_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','equipment_status')">{{form['equipment_status']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','equipment_quantity') || $check_field('add','equipment_quantity') || $check_field('set','equipment_quantity')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="设备数量" prop="equipment_quantity">
								<el-input-number id="equipment_quantity" v-model.number="form['equipment_quantity']"
						v-if="(form['device_information_id'] && $check_field('set','equipment_quantity')) || (!form['device_information_id'] && $check_field('add','equipment_quantity'))" :disabled="disabledObj['equipment_quantity_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','equipment_quantity')">{{form['equipment_quantity']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','equipment_pictures') || $check_field('add','equipment_pictures') || $check_field('set','equipment_pictures')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="设备图片" prop="equipment_pictures">
								<el-upload :disabled="disabledObj['equipment_pictures_isDisabled']" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_equipment_pictures"
						:show-file-list="false" v-if="(form['device_information_id'] && $check_field('set','equipment_pictures')) || (!form['device_information_id'] && $check_field('add','equipment_pictures'))">
						<img id="equipment_pictures" v-if="form['equipment_pictures']" :src="$fullUrl(form['equipment_pictures'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','equipment_pictures')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['equipment_pictures'])" :preview-src-list="[$fullUrl(form['equipment_pictures'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','usage') || $check_field('add','usage') || $check_field('set','usage')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="使用情况" prop="usage">
								<el-input type="textarea" id="usage" v-model="form['usage']" placeholder="请输入使用情况"
						v-if="(form['device_information_id'] && $check_field('set','usage')) || (!form['device_information_id'] && $check_field('add','usage'))" :disabled="disabledObj['usage_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','usage')">{{form['usage']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','equipment_introduction') || $check_field('add','equipment_introduction') || $check_field('set','equipment_introduction')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="设备介绍" prop="equipment_introduction">
								<el-input type="textarea" id="equipment_introduction" v-model="form['equipment_introduction']" placeholder="请输入设备介绍"
						v-if="(form['device_information_id'] && $check_field('set','equipment_introduction')) || (!form['device_information_id'] && $check_field('add','equipment_introduction'))" :disabled="disabledObj['equipment_introduction_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','equipment_introduction')">{{form['equipment_introduction']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','device_details') || $check_field('add','device_details') || $check_field('set','device_details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="设备详情" prop="device_details">
					<quill-editor v-model.number="form['device_details']"
						v-if="(form['device_information_id'] && $check_field('set','device_details')) || (!form['device_information_id'] && $check_field('add','device_details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','device_details')" v-html="form['device_details']"></div>
				</el-form-item>
			</el-col>
						
	
	
		
		
	
	
	
	
	</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/device_information/view','set') || $check_action('/device_information/view','add') || $check_option('/device_information/table','examine')">
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
				field: "device_information_id",
				url_add: "~/api/device_information/add?",
				url_set: "~/api/device_information/set?",
				url_get_obj: "~/api/device_information/get_obj?",
				url_upload: "~/api/device_information/upload?",

				query: {
					"device_information_id": 0,
				},

				form: {
								"equipment_number":  '', // 设备编号
										"equipment_name":  '', // 设备名称
										"equipment_type":  '', // 设备类型
										"equipment_status":  '', // 设备状态
										"equipment_quantity":  0, // 设备数量
										"equipment_pictures":  '', // 设备图片
										"usage":  '', // 使用情况
										"equipment_introduction":  '', // 设备介绍
										"device_details":  '', // 设备详情
											"device_information_id": 0, // ID
													},
				disabledObj:{
								"equipment_number_isDisabled": false,
										"equipment_name_isDisabled": false,
										"equipment_type_isDisabled": false,
										"equipment_status_isDisabled": false,
					          			"equipment_quantity_isDisabled": false,
										"equipment_pictures_isDisabled": false,
										"usage_isDisabled": false,
										"equipment_introduction_isDisabled": false,
										"device_details_isDisabled": false,
										},

	
				
				
				
				
				
				
				
				
			
			}
		},
		methods: {

	
	
			
	
			
	
			
	
			
	
						/**
			 * 上传设备图片
			 * @param {Object} param 图片参数
			 */
			upload_equipment_pictures(param){
									this.uploadFile(param.file, "equipment_pictures");
								},
	
	
			
	
			
	
			
	
		
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
					bl = this.$check_action('/device_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/device_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/device_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/device_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/device_information/view','get');
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
