from django.shortcuts import render, get_object_or_404, redirect
from django.views.decorators.csrf import csrf_exempt, csrf_protect
from django.contrib.auth.hashers import make_password

from .models import Post, Comment, User
from .forms import *

def post_list(request):
    return render(request, 'post_list.html', {'posts': Post.objects.all()})

def post_detail(request, pk):
    post = get_object_or_404(Post, pk=pk)
    comments = Comment.objects.filter(post=post)
    return render(request, 'post_detail.html', {'post': post, 'comments': comments})

def render_register(request):
    if request.method == 'POST':
        form = RegisterForm(request.POST)
        if form.is_valid():
            username = form.cleaned_data.get('username')
            email = form.cleaned_data.get('email')
            password = form.cleaned_data.get('password')
            birth_date = form.cleaned_data.get('birth_date')

            if User.objects.filter(username=username).exists():
                form.add_error('username', 'Username already taken')
            else:
               User(
                    username = username,
                    password = make_password(password),
                    email = email,
                    birth_date = birth_date
               ).save()
               return redirect('login')
        else:
            raise forms.ValidationError('Please enter valid data')
    else:
        form = RegisterForm()

    return render(request, 'register_form.html', {'form' : form})

@csrf_protect
def login_post(request):
    if request.method == 'POST':
        form = LoginForm(request.POST)

        if form.is_valid():
            username = form.cleaned_data.get('username')
            password = form.cleaned_data.get('password')

            try:
                user = User.objects.get(username=username)
                if not user.check_password(password):
                    form.add_error('password', 'Incorrect password')
                else:
                    request.session['pk'] = user.pk
                    return redirect('home')
            except User.DoesNotExist:
                form.add_error('username', 'Username does not exist')
        else:
            raise forms.ValidationError('Please enter valid data')
    else:
        form = LoginForm()

    return render(request, 'login_form.html', context={'form': form})

def post_new(request):
    pass