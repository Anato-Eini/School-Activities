using AutoMapper;
using ANI.Models;
using ANI.DTO;
using Microsoft.VisualStudio.Web.CodeGenerators.Mvc.Templates.Blazor;

namespace ANI.Mappings;

/// <summary>
/// Defines the mapping profile for AutoMapper to map between different models and DTOs.
/// </summary>
public class MappingProfile : Profile
{
    /// <summary>
    /// Initializes a new instance of the <see cref="MappingProfile"/> class.
    /// Configures the mappings between models and DTOs.
    /// </summary>
    public MappingProfile()
    {
        // User mappings
        CreateMap<User, UserResponseDTO>();
        CreateMap<UserCreateDTO, User>();
        CreateMap<User, UserCreateDTO>();
        CreateMap<UserLoginDTO, User>();
        CreateMap<User, UserSecDTO>();
        CreateMap<UserSecDTO, User>();

        // Product mappings
        CreateMap<Product, ProductCreateDTO>();
        CreateMap<ProductCreateDTO, Product>();
        CreateMap<Product, ProductResponseDTO>();
        CreateMap<ProductUpdateDTO, Product>();
        CreateMap<Product, ProductUpdateDTO>();
        CreateMap<Product, ProductSecDTO>();
        CreateMap<ProductSecDTO, Product>();

        // Rating mappings
        CreateMap<Rating, RatingDTO>();
        CreateMap<RatingDTO, Rating>();
    }
}
