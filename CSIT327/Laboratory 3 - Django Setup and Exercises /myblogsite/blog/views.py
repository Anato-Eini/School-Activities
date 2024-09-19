from django.shortcuts import render, get_object_or_404, redirect
from django.contrib.auth.hashers import make_password
from django.contrib.auth.password_validation import validate_password

from .models import Post, Comment, User
from .forms import *

def post_list(request):
    return render(request, 'post_list.html', {'posts': Post.objects.all()})

def post_detail(request, pk):
    post = get_object_or_404(Post, pk=pk)
    comments = Comment.objects.filter(post=post)
    return render(request, 'post_detail.html', {'post': post, 'comments': comments})

def render_login(request):
    return render(request, 'login_form.html', {'form' : LoginForm()})

def render_register(request):
    message = ''
    if request.method == 'POST':
        form = RegisterForm(request.POST)
        if form.is_valid():
            username = form.cleaned_data.get('username')
            email = form.cleaned_data.get('email')
            password = form.cleaned_data.get('password')
            birth_date = form.cleaned_data.get('birth_date')

            if User.objects.filter(username=username).exists():
                message = 'Username already taken'
            else:
               User(
                    username = username,
                    password = make_password(password),
                    email = email,
                    birth_date = birth_date
               ).save()
               return render(request, 'login_form.html')
        else:
            message = 'Invalid Form'

    return render(request, 'register_form.html', {'message': message, 'form' : RegisterForm()})

def login_post(request):
    if request.method == 'POST':
        form = LoginForm(request.POST)
        if form.is_valid():
            username = form.cleaned_data.get('username')
            password = form.cleaned_data.get('password')

            try:
                user = User.objects.get(username=username)
                if user and user.check_password(password):
                    request.session['username'] = username
                    return redirect('home')
            except:
                form.add_error('username', 'Username does not exist')
        else:
            message = 'Invalid Form'
            form = LoginForm()

    return render(request, 'login.html', context={'form': form, 'message': message})

def post_new(request):
    pass