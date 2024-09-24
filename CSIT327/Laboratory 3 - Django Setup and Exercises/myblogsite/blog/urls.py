from django.urls import path
from . import views

urlpatterns = [
    path('', views.login_post, name='login'),
    path('register/', views.render_register, name='register'),
    path('home/', views.post_list, name='home'),
    path('post/<int:pk>/', views.post_detail, name='post_detail')
]

