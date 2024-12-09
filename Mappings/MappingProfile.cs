using AutoMapper;
using ANI.Models;
using ANI.DTO;
using Microsoft.VisualStudio.Web.CodeGenerators.Mvc.Templates.Blazor;

namespace ANI.Mappings;
public class MappingProfile : Profile
{
    public MappingProfile()
    {
        CreateMap<User, UserResponseDTO>();
        CreateMap<UserCreateDTO, User>();
        CreateMap<User, UserCreateDTO>();
        CreateMap<UserLoginDTO, User>();
        CreateMap<User, UserSecDTO>();
        CreateMap<UserSecDTO, User>();

        CreateMap<Product, ProductCreateDTO>();
        CreateMap<ProductCreateDTO, Product>();
        CreateMap<Product, ProductResponseDTO>();
        CreateMap<ProductUpdateDTO, Product>();
        CreateMap<Product, ProductUpdateDTO>();
        CreateMap<Product, ProductSecDTO>();
        CreateMap<ProductSecDTO, Product>();
    }
}
