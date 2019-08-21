require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-tone2"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.description  = <<-DESC
                  react-native-tone2
                   DESC
  s.homepage     = "https://github.com/six519/react-native-tone2"
  s.license      = "MIT"
  # s.license    = { :type => "MIT", :file => "FILE_LICENSE" }
  s.authors      = { "Ferdinand Silva" => "ferdinandsilva@ferdinandsilva.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/six519/react-native-tone2.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,m,swift}"
  s.requires_arc = true

  s.dependency "React"
	
  # s.dependency "..."
end

