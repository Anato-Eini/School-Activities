namespace ANI.Libraries;

/// <summary>
/// Provides utility methods throughout the application
/// </summary>
public static class Library
{
    private static readonly string BaseUrl = "http://localhost:5088/";

    /// <summary>
    /// Prepends the base URL to the specified URL.
    /// </summary>
    /// <param name="url">The URL to prepend the base URL to.</param>
    /// <returns>The combined URL.</returns>
    public static string PrependUrl(string url)
    {
        return string.Concat(BaseUrl, url);
    }

    /// <summary>
    /// Saves the specified image to the specified directory.
    /// </summary>
    /// <param name="directory">The directory to save the image in.</param>
    /// <param name="image">The image to save.</param>
    /// <returns>The file path of the saved image.</returns>
    public static async Task<string> SaveImage(string directory, IFormFile image)
    {
        string directoryPath = Path.Combine("Media", "Images", directory);

        if (!Directory.Exists(directoryPath))
            Directory.CreateDirectory(directoryPath);

        string filePath = Path.Combine(directoryPath, $"{Guid.NewGuid()}_{image.FileName}");

        using (FileStream stream = new(filePath, FileMode.Create))
        {
            await image.CopyToAsync(stream);
        }
        return filePath;
    }
}