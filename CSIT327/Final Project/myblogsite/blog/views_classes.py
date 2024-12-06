from django.contrib.auth import login, logout, authenticate
from django.contrib.auth.views import LogoutView, LoginView
from django.shortcuts import render, redirect, get_object_or_404

from .forms import CustomAuthenticationForm
from .models import UserBan

class CustomLoginView(LoginView):
    """
    Custom Login View inherited from Django LoginView
    """

    template_name = 'login_form.html'
    form_class = CustomAuthenticationForm

    def get(self, request, *args, **kwargs):
        return render(request, self.template_name, {'form': self.form_class()})

    def post(self, request, *args, **kwargs):
        form = self.form_class(data=request.POST)
        if form.is_valid():
            username = form.cleaned_data['username']
            password = form.cleaned_data['password']

            user = authenticate(username=username, password=password)
            if user is not None:
                login(request, user)

                if request.user.is_banned:
                    userban = get_object_or_404(UserBan, user_ref=request.user)
                    return render(request, 'banned.html',{'userban': userban})

                return redirect('home', pk=0, page_number=1)
            else:
                form.add_error(None, 'Invalid username or password')

        return render(request, self.template_name, {'form': form})


class CustomLogoutView(LogoutView):
    """
    Custom Logout View inherited from Django LogoutView
    """

    def dispatch(self, request, *args, **kwargs):
        logout(request)
        response = super().dispatch(request, *args, **kwargs)
        return redirect('home', pk=0, page_number=1)