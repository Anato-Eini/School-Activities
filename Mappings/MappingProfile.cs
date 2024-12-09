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
        CreateMap<Product, ProductDTO>();
        CreateMap<ProductDTO, Product>();
        CreateMap<User, UserSecDTO>();
        CreateMap<UserSecDTO, User>();
    }
}
