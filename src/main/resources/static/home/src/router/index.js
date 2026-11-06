import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue'
import login from '../views/account/login.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index
	},
	// 登录
	{
		path: '/account/login',
		name: 'login',
		component: login
	},
	// 忘记密码
	{
		path: '/account/forgot',
		name: 'forgot',
		component: () => import('../views/account/forgot.vue')
	},
	// 注册账号
	{
		path: '/account/register',
		name: 'register',
		component: () => import('../views/account/register.vue')
	},
		// 媒体图片
	{
		path: '/media/image',
		name: 'media_image',
		component: () => import('../views/media/image.vue')
	},
	// 音乐
	{
		path: '/media/music',
		name: 'media_music',
		component: () => import('../views/media/music.vue')
	},
	// 媒体视频
	{
		path: '/media/video',
		name: 'media_video',
		component: () => import('../views/media/video.vue')
	},
	// 媒体视频
	{
		path: '/user_center/index',
		name: 'user_center_index',
		component: () => import('../views/user_center/index.vue')
	},
	// 文章路由
	{
		path: '/article/list',
		name: 'article_list',
		component: () => import('../views/article/list.vue')
	},
	{
		path: '/article/details',
		name: 'article_details',
		component: () => import('../views/article/details.vue')
	},
	// 浏览网站
	// 收藏路由
	{
		path: '/user/collect',
		name: 'collect_list',
		component: () => import('../views/user/collect.vue')
	},


	{
		path: '/comment/table',
		name: 'comment_table',
		component: () => import('../views/comment/table.vue')
	},
	{
		path: '/comment/view',
		name: 'comment_view',
		component: () => import('../views/comment/view.vue')
	},

	



	// 公告路由
	{
		path: '/notice/list',
		name: 'notice_list',
		component: () => import('../views/notice/list.vue')
	},
	{
		path: '/notice/details',
		name: 'notice_details',
		component: () => import('../views/notice/details.vue')
	},
	// 会员用户表格路由
	{
		path: '/member_user/table',
		name: '/member_user_table',
		component: () => import('../views/member_user/table.vue')
	},
	// 会员用户详情路由
	{
		path: '/member_user/view',
		name: '/member_user_view',
		component: () => import('../views/member_user/view.vue')
	},
	
	
		
		
		
	// 课程信息表格路由
	{
		path: '/course_information/table',
		name: '/course_information_table',
		component: () => import('../views/course_information/table.vue')
	},
	// 课程信息详情路由
	{
		path: '/course_information/view',
		name: '/course_information_view',
		component: () => import('../views/course_information/view.vue')
	},
	
		// 课程信息列表路由
	{
		path: '/course_information/list',
		name: '/course_information_list',
		component: () => import('../views/course_information/list.vue')
	},
	
		// 课程信息详情路由
	{
		path: '/course_information/details',
		name: '/course_information_details',
		component: () => import('../views/course_information/details.vue')
	},
		
		
		
	// 课程参与表格路由
	{
		path: '/course_participation/table',
		name: '/course_participation_table',
		component: () => import('../views/course_participation/table.vue')
	},
	// 课程参与详情路由
	{
		path: '/course_participation/view',
		name: '/course_participation_view',
		component: () => import('../views/course_participation/view.vue')
	},
		// 课程参与添加路由
	{
		path: '/course_participation/edit',
		name: '/course_participation_edit',
		component: () => import('../views/course_participation/edit.vue')
	},
	
	
		
		
		
	// 健身建议表格路由
	{
		path: '/fitness_advice/table',
		name: '/fitness_advice_table',
		component: () => import('../views/fitness_advice/table.vue')
	},
	// 健身建议详情路由
	{
		path: '/fitness_advice/view',
		name: '/fitness_advice_view',
		component: () => import('../views/fitness_advice/view.vue')
	},
	
	
		
		
		
	// 会员卡表格路由
	{
		path: '/membership_card/table',
		name: '/membership_card_table',
		component: () => import('../views/membership_card/table.vue')
	},
	// 会员卡详情路由
	{
		path: '/membership_card/view',
		name: '/membership_card_view',
		component: () => import('../views/membership_card/view.vue')
	},
	
		// 会员卡列表路由
	{
		path: '/membership_card/list',
		name: '/membership_card_list',
		component: () => import('../views/membership_card/list.vue')
	},
	
		// 会员卡详情路由
	{
		path: '/membership_card/details',
		name: '/membership_card_details',
		component: () => import('../views/membership_card/details.vue')
	},
		
		
		
	// 设备信息表格路由
	{
		path: '/device_information/table',
		name: '/device_information_table',
		component: () => import('../views/device_information/table.vue')
	},
	// 设备信息详情路由
	{
		path: '/device_information/view',
		name: '/device_information_view',
		component: () => import('../views/device_information/view.vue')
	},
	
	
		
		
		
	// 财务报表表格路由
	{
		path: '/financial_statements/table',
		name: '/financial_statements_table',
		component: () => import('../views/financial_statements/table.vue')
	},
	// 财务报表详情路由
	{
		path: '/financial_statements/view',
		name: '/financial_statements_view',
		component: () => import('../views/financial_statements/view.vue')
	},
	
	
		
		
		

	// 用户路由
	{
		path: '/user/index',
		name: 'user_index',
		component: () => import('../views/user/index.vue')
	},
	// 基本信息
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue')
	},
	// 找回密码
	{
		path: '/user/password',
		name: 'user_password',
		component: () => import('../views/user/password.vue')
	},

	// 搜索
	{
		path: '/search',
		name: 'search',
		component: () => import('../views/search/index.vue')
	},
	// 局部搜索
	{
		path: '/search/details',
		name: 'search_details',
		component: () => import('../views/search/details.vue')
	}
]

const router = new VueRouter({
	mode: 'hash',
	base: process.env.BASE_URL,
	routes
})

router.afterEach((to, from, next) => {
	let title = "健身房管理系统-home";
	document.title = title;
	document.logo = "健身房管理系统"
})

export default router
