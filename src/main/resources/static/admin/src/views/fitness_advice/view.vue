<template>
	<el-main class="bg edit_wrap comtable_e">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce"> 
							<el-col v-if="$check_field('get','member_user') || $check_field('add','member_user') || $check_field('set','member_user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="会员用户" prop="member_user">
																<el-select v-if="(form['fitness_advice_id'] && $check_field('set','member_user')) || (!form['fitness_advice_id'] && $check_field('add','member_user'))" id="member_user" v-model="form['member_user']" :disabled="disabledObj['member_user_isDisabled']">
							<el-option v-for="o in list_user_member_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','member_user')" id="member_user" v-model="form['member_user']" :disabled="true">
							<el-option v-for="o in list_user_member_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','member_name') || $check_field('add','member_name') || $check_field('set','member_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="会员姓名" prop="member_name">
															<el-input id="member_name" v-model="form['member_name']" placeholder="请输入会员姓名"
							  v-if="(form['fitness_advice_id'] && $check_field('set','member_name')) || (!form['fitness_advice_id'] && $check_field('add','member_name'))" :disabled="disabledObj['member_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','member_name')">{{form['member_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','suggested_title') || $check_field('add','suggested_title') || $check_field('set','suggested_title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="建议标题" prop="suggested_title">
															<el-input id="suggested_title" v-model="form['suggested_title']" placeholder="请输入建议标题"
							  v-if="(form['fitness_advice_id'] && $check_field('set','suggested_title')) || (!form['fitness_advice_id'] && $check_field('add','suggested_title'))" :disabled="disabledObj['suggested_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','suggested_title')">{{form['suggested_title']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','recommend_courses') || $check_field('add','recommend_courses') || $check_field('set','recommend_courses')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="推荐课程" prop="recommend_courses">
															<el-input id="recommend_courses" v-model="form['recommend_courses']" placeholder="请输入推荐课程"
							  v-if="(form['fitness_advice_id'] && $check_field('set','recommend_courses')) || (!form['fitness_advice_id'] && $check_field('add','recommend_courses'))" :disabled="disabledObj['recommend_courses_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','recommend_courses')">{{form['recommend_courses']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','suggested_content') || $check_field('add','suggested_content') || $check_field('set','suggested_content')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
							<el-form-item label="建议内容" prop="suggested_content">
											<el-input type="textarea" id="suggested_content" v-model="form['suggested_content']" placeholder="请输入建议内容"
						v-if="(form['fitness_advice_id'] && $check_field('set','suggested_content')) || (!form['fitness_advice_id'] && $check_field('add','suggested_content'))" :disabled="disabledObj['suggested_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','suggested_content')">{{form['suggested_content']}}</div>
							</el-form-item>
			</el-col>
						
	
	
		
		
	
	
	
		
		
	
		</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/fitness_advice/view','set') || $check_action('/fitness_advice/view','add') || $check_option('/fitness_advice/table','examine')">
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
				field: "fitness_advice_id",
				url_add: "~/api/fitness_advice/add?",
				url_set: "~/api/fitness_advice/set?",
				url_get_obj: "~/api/fitness_advice/get_obj?",
				url_upload: "~/api/fitness_advice/upload?",

				query: {
					"fitness_advice_id": 0,
				},

				form: {
								"member_user": 0, // 会员用户
										"member_name":  '', // 会员姓名
										"suggested_title":  '', // 建议标题
										"recommend_courses":  '', // 推荐课程
										"suggested_content":  '', // 建议内容
											"fitness_advice_id": 0, // ID
															},
				disabledObj:{
								"member_user_isDisabled": false,
										"member_name_isDisabled": false,
										"suggested_title_isDisabled": false,
										"recommend_courses_isDisabled": false,
										"suggested_content_isDisabled": false,
										},

	
					// 用户列表
				list_user_member_user: [],
					
			
			
			
		
			}
		},
		methods: {

	
	
				/**
			 * 获取会员用户用户列表
			 */
			async get_list_user_member_user() {
                var json = await this.$get("~/api/user/get_list?user_group=会员用户");
                if(json.result && json.result.list){
                    this.list_user_member_user = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					get_user_member_user(id){
				var obj = this.list_user_member_user.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
			
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
												// 获取缓存数据附加
				form = $.db.get("form");
									$.push(this.form ,form);
										
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
					bl = this.$check_action('/fitness_advice/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/fitness_advice/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/fitness_advice/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/fitness_advice/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/fitness_advice/view','get');
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
					this.get_list_user_member_user();
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
