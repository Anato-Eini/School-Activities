namespace ANI.Libraries;

public static class Library
{

    private static readonly string BaseUrl = "http://localhost:5088/";

    public static string PrependUrl(string url)
    {
        return string.Concat(BaseUrl, url);
    }

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