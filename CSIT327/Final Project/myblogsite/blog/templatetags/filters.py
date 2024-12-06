from django import template

register = template.Library()

@register.filter
def _enumerate(list_a):
    return enumerate(list_a)