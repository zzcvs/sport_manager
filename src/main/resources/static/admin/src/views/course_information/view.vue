<template>
	<el-main class="bg edit_wrap comtable_e">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce"> 
							<el-col v-if="$check_field('get','course_name') || $check_field('add','course_name') || $check_field('set','course_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="课程名称" prop="course_name">
															<el-input id="course_name" v-model="form['course_name']" placeholder="请输入课程名称"
							  v-if="(form['course_information_id'] && $check_field('set','course_name')) || (!form['course_information_id'] && $check_field('add','course_name'))" :disabled="disabledObj['course_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_name')">{{form['course_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','course_type') || $check_field('add','course_type') || $check_field('set','course_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="课程类型" prop="course_type">
															<el-input id="course_type" v-model="form['course_type']" placeholder="请输入课程类型"
							  v-if="(form['course_information_id'] && $check_field('set','course_type')) || (!form['course_information_id'] && $check_field('add','course_type'))" :disabled="disabledObj['course_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_type')">{{form['course_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','course_time') || $check_field('add','course_time') || $check_field('set','course_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="课程时间" prop="course_time">
															<el-input id="course_time" v-model="form['course_time']" placeholder="请输入课程时间"
							  v-if="(form['course_information_id'] && $check_field('set','course_time')) || (!form['course_information_id'] && $check_field('add','course_time'))" :disabled="disabledObj['course_time_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_time')">{{form['course_time']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','course_location') || $check_field('add','course_location') || $check_field('set','course_location')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="课程地点" prop="course_location">
															<el-input id="course_location" v-model="form['course_location']" placeholder="请输入课程地点"
							  v-if="(form['course_information_id'] && $check_field('set','course_location')) || (!form['course_information_id'] && $check_field('add','course_location'))" :disabled="disabledObj['course_location_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_location')">{{form['course_location']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','name_of_coach') || $check_field('add','name_of_coach') || $check_field('set','name_of_coach')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="教练姓名" prop="name_of_coach">
															<el-input id="name_of_coach" v-model="form['name_of_coach']" placeholder="请输入教练姓名"
							  v-if="(form['course_information_id'] && $check_field('set','name_of_coach')) || (!form['course_information_id'] && $check_field('add','name_of_coach'))" :disabled="disabledObj['name_of_coach_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_coach')">{{form['name_of_coach']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','course_picture') || $check_field('add','course_picture') || $check_field('set','course_picture')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="课程图片" prop="course_picture">
											<el-upload :disabled="disabledObj['course_picture_isDisabled']" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_course_picture"
						:show-file-list="false" v-if="(form['course_information_id'] && $check_field('set','course_picture')) || (!form['course_information_id'] && $check_field('add','course_picture'))">
						<img id="course_picture" v-if="form['course_picture']" :src="$fullUrl(form['course_picture'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','course_picture')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['course_picture'])" :preview-src-list="[$fullUrl(form['course_picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','course_introduction') || $check_field('add','course_introduction') || $check_field('set','course_introduction')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="课程介绍" prop="course_introduction">
											<el-input type="textarea" id="course_introduction" v-model="form['course_introduction']" placeholder="请输入课程介绍"
						v-if="(form['course_information_id'] && $check_field('set','course_introduction')) || (!form['course_information_id'] && $check_field('add','course_introduction'))" :disabled="disabledObj['course_introduction_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_introduction')">{{form['course_introduction']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','course_details') || $check_field('add','course_details') || $check_field('set','course_details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
							<el-form-item label="课程详情" prop="course_details">
								<quill-editor v-model.number="form['course_details']"
						v-if="(form['course_information_id'] && $check_field('set','course_details')) || (!form['course_information_id'] && $check_field('add','course_details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','course_details')" v-html="form['course_details']"></div>
				</el-form-item>
			</el-col>
						
	
	
		
		
						<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp" v-if="!form['source_user_id'] && 0 > 0">
			  <el-form-item label="参与限次">
				<el-input id="limit_times" v-model="form['course_participation_limit_times']" placeholder="参与限制次数，0为不限"
						  v-if="$check_option('/course_information/view','can_limits')"
				></el-input>
				<div v-else-if="$check_action('/course_information/view','get')" v-html="form['course_participation_limit_times']"></div>
			  </el-form-item>
			</el-col>
			
	
	
		
		
	
		</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/course_information/view','set') || $check_action('/course_information/view','add') || $check_option('/course_information/table','examine')">
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
				field: "course_information_id",
				url_add: "~/api/course_information/add?",
				url_set: "~/api/course_information/set?",
				url_get_obj: "~/api/course_information/get_obj?",
				url_upload: "~/api/course_information/upload?",

				query: {
					"course_information_id": 0,
				},

				form: {
								"course_name":  '', // 课程名称
										"course_type":  '', // 课程类型
										"course_time":  '', // 课程时间
										"course_location":  '', // 课程地点
										"name_of_coach":  '', // 教练姓名
										"course_picture":  '', // 课程图片
										"course_introduction":  '', // 课程介绍
										"course_details":  '', // 课程详情
											"course_information_id": 0, // ID
													"course_participation_limit_times": 0, // 参与限制次数
												},
				disabledObj:{
								"course_name_isDisabled": false,
										"course_type_isDisabled": false,
										"course_time_isDisabled": false,
										"course_location_isDisabled": false,
										"name_of_coach_isDisabled": false,
										"course_picture_isDisabled": false,
										"course_introduction_isDisabled": false,
										"course_details_isDisabled": false,
										},

	
			
			
			
			
			
			
			
		
			}
		},
		methods: {

	
	
			
	
			
	
			
	
			
	
						/**
			 * 上传课程图片
			 * @param {Object} param 图片参数
			 */
			upload_course_picture(param){
									this.uploadFile(param.file, "course_picture");
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
					bl = this.$check_action('/course_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/course_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/course_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/course_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/course_information/view','get');
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
