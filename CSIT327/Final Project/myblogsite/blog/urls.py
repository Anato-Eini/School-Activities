from django.conf.urls.static import static
from django.urls import path

from myblogsite import settings
from . import views
from .views_classes import CustomLoginView, CustomLogoutView

urlpatterns = [
    path('', views.go_default_page, name='default_page'),
    path('login/', CustomLoginView.as_view(), name='login'),
    path('register/', views.render_register, name='register'),
    path('new_post/', views.new_post_form, name='new_post'),
    path('home/<int:pk>/<int:page_number>/', views.fetch_posts, name='home'),
    path('post_detail/<int:pk>/<int:page_number>/',
         views.post_detail, name='post_detail'),
    path('profile/<int:pk>/', views.render_profile, name='profile'),
    path('logout/', CustomLogoutView.as_view(next_page='home'), name='logout'),

    # AJAX
    path('post_vote/', views.post_vote, name='post_vote'),
    path('comment_vote/', views.comment_vote, name='comment_vote'),
    path('view_num/', views.num_view, name='fetch_view_num'),
    path('comment_num/', views.num_comments, name='fetch_comment_num'),

    path('post/<int:pk>/update_post/', views.update_post, name='update_post'),
    path('post/<int:pk>/update_title/',
         views.update_post_title, name='update_post_title'),
    path('post/<int:pk>/update_content/',
         views.update_post_content, name='update_post_content'),
    path('post/<int:pk>/delete/', views.delete_post, name='delete_post'),
    path('post/<int:pk>/perma_delete/', views.perma_delete, name='perma_delete'),

    path('edit_comment/<int:comment_id>/<int:user_post_id>',
         views.edit_comment, name='edit_comment'),
    path('delete_comment/<int:comment_id>/<int:user_post_id>/',
         views.delete_comment, name="delete_comment"),

    path('add_favorite/<int:post_id>', views.add_favorite, name="add_favorite"),
    path('post/<int:pk>/restore_post/', views.restore_post, name='restore_post'),
    path('adminpanel/', views.render_adminpanel, name="adminpanel"),
    path('ban_user/<int:pk>', views.ban_user, name="ban_user"),
    path('unban_user/<int:pk>', views.unban_user, name="unban_user"),
    path('ban_appeal/', views.ban_appeal, name="ban_appeal"),
    path('post/report_post/<int:userpost_id>/',
         views.report_post, name='report_post'),
    path('post/ban_user_from_post_report/<int:user_pk>/<int:userpost_pk>/',
         views.ban_user_from_post_report, name='ban_user_from_post_report'),
    path('post/perma_delete_from_post_report/<int:pk>/',
         views.perma_delete_from_post_report, name='perma_delete_from_post_report'),
    path('post/delete_reportpost/<int:pk>/',
         views.delete_reportpost, name='delete_reportpost'),

    path('post/report_comment/<int:comment_id>',
         views.report_comment, name='report_comment'),
    path('post/delete_reportcomment/<int:report_id>/',
         views.delete_reportcomment, name='delete_reportcomment'),
    path('post/delete_comment_from_report/<int:comment_id>/',
         views.delete_comment_from_comment_report, name='delete_comment_from_comment_report'),
    path('post/ban_user_from_comment_report/<int:user_id>/<int:comment_id>/',
         views.ban_user_from_comment_report, name="ban_user_from_comment_report"),
    path('reject_appeal/<int:pk>', views.reject_appeal, name="reject_appeal"),
    path('accept_appeal/<int:pk>', views.accept_appeal, name="accept_appeal"),

] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
