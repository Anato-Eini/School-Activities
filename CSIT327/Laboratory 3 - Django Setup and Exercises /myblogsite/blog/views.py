from django.shortcuts import render, get_object_or_404

from .models import Post, Comment
from .forms import *

def post_list(request):
    posts = Post.objects.all()
    return render(request, 'post_list.html', {'posts': posts})

def post_detail(request, pk):
    post = get_object_or_404(Post, pk=pk)
    comments = Comment.objects.filter(post=post)
    return render(request, 'post_detail.html', {'post': post, 'comments': comments})

def render_login(request):
    return render(request, 'login_form.html', {'form' : LoginForm()})

def render_register(request):
    return render(request, 'register_form.html', {'form' : RegisterForm()})

def login_post(request):
    if request.method == 'POST':
        form = LoginForm(request.POST)
        if form.is_valid():
            name = form.cleaned_data.get('name')
            password = form.cleaned_data.get('password')
            birth_date = form.cleaned_data.get('birth_date')
            email = form.cleaned_data.get('email')
            return render(request, '/success.html', {'name' : name})
        else:
            form = LoginForm()

        return render(request, 'login.html', )

def post_new(request):
    pass