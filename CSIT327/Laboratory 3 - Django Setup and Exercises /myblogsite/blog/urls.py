from django.urls import path
from . import views

urlpatterns = [
    path('', views.render_login, name='login'),
    path('register', views.render_register, name='register'),
    path('post/<int:pk>/', views.post_detail, name='post_detail')
]

