<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce">
							<el-col v-if="$check_field('get','course_name') || $check_field('add','course_name') || $check_field('set','course_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="课程名称" prop="course_name">
												<el-input id="course_name" v-model="form['course_name']" placeholder="请输入课程名称"
							  v-if="(form['course_participation_id'] && $check_field('set','course_name')) || (!form['course_participation_id'] && $check_field('add','course_name'))" :disabled="disabledObj['course_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_name')">{{form['course_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','course_type') || $check_field('add','course_type') || $check_field('set','course_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="课程类型" prop="course_type">
												<el-input id="course_type" v-model="form['course_type']" placeholder="请输入课程类型"
							  v-if="(form['course_participation_id'] && $check_field('set','course_type')) || (!form['course_participation_id'] && $check_field('add','course_type'))" :disabled="disabledObj['course_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_type')">{{form['course_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','course_time') || $check_field('add','course_time') || $check_field('set','course_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="课程时间" prop="course_time">
												<el-input id="course_time" v-model="form['course_time']" placeholder="请输入课程时间"
							  v-if="(form['course_participation_id'] && $check_field('set','course_time')) || (!form['course_participation_id'] && $check_field('add','course_time'))" :disabled="disabledObj['course_time_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_time')">{{form['course_time']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','course_location') || $check_field('add','course_location') || $check_field('set','course_location')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="课程地点" prop="course_location">
												<el-input id="course_location" v-model="form['course_location']" placeholder="请输入课程地点"
							  v-if="(form['course_participation_id'] && $check_field('set','course_location')) || (!form['course_participation_id'] && $check_field('add','course_location'))" :disabled="disabledObj['course_location_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_location')">{{form['course_location']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','name_of_coach') || $check_field('add','name_of_coach') || $check_field('set','name_of_coach')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="教练姓名" prop="name_of_coach">
												<el-input id="name_of_coach" v-model="form['name_of_coach']" placeholder="请输入教练姓名"
							  v-if="(form['course_participation_id'] && $check_field('set','name_of_coach')) || (!form['course_participation_id'] && $check_field('add','name_of_coach'))" :disabled="disabledObj['name_of_coach_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_coach')">{{form['name_of_coach']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','member_user') || $check_field('add','member_user') || $check_field('set','member_user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="会员用户" prop="member_user">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_member_user(form['member_user']) }}
							<el-select v-if="(form['course_participation_id'] && $check_field('set','member_user')) || (!form['course_participation_id'] && $check_field('add','member_user'))" id="member_user" v-model="form['member_user']" :disabled="disabledObj['member_user_isDisabled']">
								<el-option v-for="o in list_user_member_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','member_user')" id="member_user" v-model="form['member_user']" :disabled="true">
								<el-option v-for="o in list_user_member_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="member_user" v-model="form['member_user']" :disabled="disabledObj['member_user_isDisabled']">
							<el-option v-for="o in list_user_member_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','member_name') || $check_field('add','member_name') || $check_field('set','member_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="会员姓名" prop="member_name">
												<el-input id="member_name" v-model="form['member_name']" placeholder="请输入会员姓名"
							  v-if="(form['course_participation_id'] && $check_field('set','member_name')) || (!form['course_participation_id'] && $check_field('add','member_name'))" :disabled="disabledObj['member_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','member_name')">{{form['member_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="$check_field('get','participation_notes') || $check_field('add','participation_notes') || $check_field('set','participation_notes')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="参与备注" prop="participation_notes">
								<el-input type="textarea" id="participation_notes" v-model="form['participation_notes']" placeholder="请输入参与备注"
						v-if="(form['course_participation_id'] && $check_field('set','participation_notes')) || (!form['course_participation_id'] && $check_field('add','participation_notes'))" :disabled="disabledObj['participation_notes_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','participation_notes')">{{form['participation_notes']}}</div>
							</el-form-item>
			</el-col>
						
	
	
		
		
						<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp" v-if="!form['source_user_id'] && 0 > 0">
			  <el-form-item label="建议限次">
				<el-input id="limit_times" v-model="form['fitness_advice_limit_times']" placeholder="建议限制次数，0为不限"
						  v-if="$check_option('/course_participation/view','can_limits')"
				></el-input>
				<div v-else-if="$check_action('/course_participation/view','get')" v-html="form['fitness_advice_limit_times']"></div>
			  </el-form-item>
			</el-col>
			
	
	
	
	</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/course_participation/view','set') || $check_action('/course_participation/view','add') || $check_option('/course_participation/table','examine')">
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
				field: "course_participation_id",
				url_add: "~/api/course_participation/add?",
				url_set: "~/api/course_participation/set?",
				url_get_obj: "~/api/course_participation/get_obj?",
				url_upload: "~/api/course_participation/upload?",

				query: {
					"course_participation_id": 0,
				},

				form: {
								"course_name":  '', // 课程名称
										"course_type":  '', // 课程类型
										"course_time":  '', // 课程时间
										"course_location":  '', // 课程地点
										"name_of_coach":  '', // 教练姓名
										"member_user": 0, // 会员用户
										"member_name":  '', // 会员姓名
										"participation_notes":  '', // 参与备注
											"course_participation_id": 0, // ID
													"fitness_advice_limit_times": 0, // 建议限制次数
										},
				disabledObj:{
								"course_name_isDisabled": false,
										"course_type_isDisabled": false,
										"course_time_isDisabled": false,
										"course_location_isDisabled": false,
										"name_of_coach_isDisabled": false,
										"member_user_isDisabled": false,
										"member_name_isDisabled": false,
										"participation_notes_isDisabled": false,
										},

	
				
				
				
				
				
					// 用户列表
				list_user_member_user: [],
						// 用户组
				group_user_member_user: "",
						
				
			
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
					/**
			 * 获取会员用户用户组
			 */
			async get_group_user_member_user() {
							this.form["member_user"] = this.$store.state.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=会员用户");
				if(json.result && json.result.obj){
					this.group_user_member_user = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_member_user(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_member_user.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
									for (let key in _this.form) {
							arrForm.push(key)
						}
												_this.form["member_user"] = id
									_this.disabledObj['member_user' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "member_user") {
			                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
			                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								  break;
								} else {
								  _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								}
							  }
							}
						  }
						}
					}
				});
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
					bl = this.$check_action('/course_participation/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/course_participation/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/course_participation/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/course_participation/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/course_participation/view','get');
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
					this.get_group_user_member_user();
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
